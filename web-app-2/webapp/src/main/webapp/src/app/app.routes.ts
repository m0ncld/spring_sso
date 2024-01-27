import {Routes} from '@angular/router';
import {HomeViewComponent} from "./views/home-view/home-view.component";
import {LoginViewComponent} from "./views/login-view/login-view.component";
import {UserListsViewComponent} from "./views/lists/user-lists-view/user-lists-view.component";
import {AllListsViewComponent} from "./views/lists/all-lists-view/all-lists-view.component";
import {AddListViewComponent} from "./views/lists/add-list-view/add-list-view.component";

export const routes: Routes = [
  {path: '', component: HomeViewComponent},
  {path: 'app', children: [
      {path: 'login', component: LoginViewComponent, },
      {path: 'lists', children: [
          {path: 'user', component: UserListsViewComponent},
          {path: 'all', component: AllListsViewComponent},
          {path: 'add', component: AddListViewComponent},
        ]}
    ]},
];
