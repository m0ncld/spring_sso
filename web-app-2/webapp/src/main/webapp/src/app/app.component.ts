import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterOutlet} from '@angular/router';
import {BaseViewComponent} from "./components/base-view/base-view.component";
import {HomeViewComponent} from "./views/home-view/home-view.component";
import {LoginViewComponent} from "./views/login-view/login-view.component";
import {UserListsViewComponent} from "./views/lists/user-lists-view/user-lists-view.component";
import {AddListViewComponent} from "./views/lists/add-list-view/add-list-view.component";
import {AllListsViewComponent} from "./views/lists/all-lists-view/all-lists-view.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    BaseViewComponent,
    HomeViewComponent
    // LoginViewComponent,
    // UserListsViewComponent,
    // AddListViewComponent,
    // AllListsViewComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title = 'webapp2';
}
