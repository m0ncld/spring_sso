import {Component} from '@angular/core';
import {BaseViewComponent} from "../../../components/base-view/base-view.component";

@Component({
  selector: 'all-lists-view',
  standalone: true,
  templateUrl: './all-lists-view.component.html',
  imports: [
    BaseViewComponent
  ],
  styleUrl: './all-lists-view.component.sass'
})
export class AllListsViewComponent {}


