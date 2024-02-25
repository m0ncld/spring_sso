import {Component} from '@angular/core';
import {BaseViewComponent} from "../../../components/base-view/base-view.component";

@Component({
  selector: 'add-list-view',
  standalone: true,
  templateUrl: './add-list-view.component.html',
  imports: [
    BaseViewComponent
  ]
})
export class AddListViewComponent {}


