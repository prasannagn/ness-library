import { NgModule } from '@angular/core';
import { EditComponent } from './edit/edit.component';
import { ListComponent } from './list/list.component';
import { DetailComponent } from './detail/detail.component';
import {SharedModule} from "../shared/shared.module";
import { RouterModule } from '@angular/router';
import {BookService} from "./book.service";
import {BookGuardService} from "./book-guard.service";

@NgModule({
  imports: [
    RouterModule.forChild([
      { path: 'books', component: ListComponent },
      { path: 'books/:id',
        canActivate: [ BookGuardService ],
        component: DetailComponent }
    ]),
    SharedModule
  ],
  providers: [
    BookService,
    BookGuardService
  ],
  entryComponents:[
    EditComponent
  ],
  declarations: [EditComponent, ListComponent, DetailComponent]
})
export class BookModule { }
