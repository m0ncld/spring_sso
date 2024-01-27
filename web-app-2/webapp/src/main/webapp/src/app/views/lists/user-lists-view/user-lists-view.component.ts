import {Component} from '@angular/core';
import {BaseViewComponent} from "../../../components/base-view/base-view.component";

@Component({
  selector: 'user-lists-view',
  standalone: true,
  templateUrl: './user-lists-view.component.html',
  imports: [
    BaseViewComponent
  ],
  styleUrl: './user-lists-view.component.sass'
})
export class UserListsViewComponent {

}


