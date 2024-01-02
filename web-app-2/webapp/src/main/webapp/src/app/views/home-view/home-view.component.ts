import {Component} from '@angular/core';
import {BaseViewComponent} from "../../components/base-view/base-view.component";

@Component({
  selector: 'home-view',
  standalone: true,
  templateUrl: './home-view.component.html',
  imports: [
    BaseViewComponent
  ],
  styleUrl: './home-view.component.sass'
})
export class HomeViewComponent {}


