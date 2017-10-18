import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {IBook} from "../book";
import {BookService} from "../book.service";
import {EditComponent} from "../edit/edit.component";


@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  errorMessage:string;
  book:IBook;

  constructor(private _route:ActivatedRoute,
              private _router:Router,
              private _bookService:BookService,
              private modalService:NgbModal) {
    this.book = {id: null, title: '', description: null, imageUrl: null, status: null, _links: null};
  }

  ngOnInit() {
    const id = +this._route.snapshot.paramMap.get('id');
    this.findOne(id);
  }

  findOne(id:number) {
    this._bookService.findOne(id).subscribe(
      book => this.book = book,
      error => this.errorMessage = <any>error
    );
  }

  edit() {
    const modalRef = this.modalService.open(EditComponent);
    modalRef.componentInstance.title = "Edit";
    modalRef.componentInstance.id = this.book.id;
  }


  deleteBook() {
    this._bookService.delete(this.book.id);
  }

  onBack():void {
    this._router.navigate(['/books']);
  }

}
