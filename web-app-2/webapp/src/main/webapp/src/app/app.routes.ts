import {Routes} from '@angular/router';
import {HomeViewComponent} from "./views/home-view/home-view.component";
import {UserListsViewComponent} from "./views/lists/user-lists-view/user-lists-view.component";
import {AllListsViewComponent} from "./views/lists/all-lists-view/all-lists-view.component";
import {AddListViewComponent} from "./views/lists/add-list-view/add-list-view.component";
import {authGuard} from "./service/auth/auth-guard.service";

/**
 * Routes configuration
 *
 * Additional data:
 * role - role of the user who has access to the view
 * tilte - view title displayed on the menu bar
 * inMenue - top bard visibility flag
 */
export const routes: Routes = [
  {path: '', component: HomeViewComponent, data: {title: 'Home', inMenue: true}},
  {path: 'app', children: [
      {path: 'lists', children: [
          {path: 'user', component: UserListsViewComponent, canActivate: [authGuard], data: {role: 'ROLE_USER',  title: 'My lists', inMenue: true}},
          {path: 'all',  component: AllListsViewComponent,  canActivate: [authGuard], data: {role: 'ROLE_ADMIN', title: 'Browse lists', inMenue: true}},
          {path: 'add',  component: AddListViewComponent,   canActivate: [authGuard], data: {role: 'ROLE_USER',  title: 'Add list', inMenue: true}},
        ]}
    ]},
];
