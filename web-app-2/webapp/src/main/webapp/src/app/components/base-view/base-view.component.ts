import {Component} from '@angular/core';
import {NavBarComponent} from "../nav-bar/nav-bar.component";

@Component({
  selector: 'base-view',
  standalone: true,
  imports: [NavBarComponent],
  templateUrl: './base-view.component.html',
  styleUrl: './base-view.component.sass'
})
export class BaseViewComponent {}


