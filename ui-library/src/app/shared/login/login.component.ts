import {Component, OnInit} from "@angular/core";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {CookieService} from "ngx-cookie-service/index";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  userName:string;
  password:string;

  ngOnInit() {

  }

  constructor(public activeModal:NgbActiveModal, private cookieService:CookieService) {

  }

  login() {
    this.cookieService.set('user', this.userName);
    console.log(this.cookieService.get('user'));
    this.activeModal.dismiss();
  }
}
