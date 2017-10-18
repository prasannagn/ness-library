import {Component, OnInit, Input, ElementRef, ViewChild} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {IBook} from "../book";
import {environment} from "../../../environments/environment";
import {BookService} from "../book.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  @Input()
  id:number;
  book:IBook;
  avatar:any;

  private multimedia_service_url = environment.multimedia_service_url + "/v1/";

  ngOnInit() {
    if (this.id) {
      this._bookService.findOne(this.id).subscribe(book => this.book = book);
    } else {
      this.book = {id: null, title: '', description: null, imageUrl: null, status: null, tags: null, _links: null};
    }
  }

  constructor(private _bookService:BookService, private _http:HttpClient) {

  }

  save() {
    let saveBook = this._bookService.save(this.book);
    saveBook.subscribe((b:IBook)=> {
      let formModel = this.prepareSave(b);
      this._http.post(this.multimedia_service_url, formModel).subscribe();
    });
  }


  @ViewChild('fileInput')
  fileInput:ElementRef;

  onFileChange(event) {
    if (event.target.files.length > 0) {
      this.avatar = event.target.files[0];
    }
  }

  private prepareSave(book:IBook):any {
    let input = new FormData();
    input.append('bookId', '' + book.id);
    input.append('data', this.avatar);
    return input;
  }

  clearFile() {
    this.avatar = null;
    this.fileInput.nativeElement.value = '';
  }

}
