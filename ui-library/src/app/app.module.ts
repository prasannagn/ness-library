import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {CookieService} from "ngx-cookie-service/index";
import {HomeComponent} from "./home/home.component";
import {BookModule} from "./book/book.module";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule.forRoot(),
    RouterModule.forRoot([
        { path: 'welcome', component: HomeComponent },
        { path: '', redirectTo: 'welcome', pathMatch: 'full'},
        { path: '**', redirectTo: 'welcome', pathMatch: 'full'}
    ]),
    SharedModule,
    BookModule
  ],
  providers:[
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
