import {Component} from '@angular/core';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from "./shared/login/login.component";
import {EditComponent} from "./book/edit/edit.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private modalService:NgbModal) {

  }

  add() {
    const modalRef = this.modalService.open(EditComponent);
    modalRef.componentInstance.title = "Add";
  }

  login() {
    const modalRef = this.modalService.open(LoginComponent);
  }
}
