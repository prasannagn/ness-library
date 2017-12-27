import { Component, OnInit } from '@angular/core';
import {IBook} from "../book";
import {BookService} from "../book.service";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  errorMessage: string;
  filteredBooks: any;
  books: IBook[] = [];

  constructor(private _bookService: BookService) {

  }
  
  ngOnInit(): void {
    this._bookService.findAll()
      .subscribe(
        books => {
          this.books = books;
          this.filteredBooks = this.books;
        },
        error => this.errorMessage = <any>error
      );
  }

}
