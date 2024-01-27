import {Component} from '@angular/core';
import {BaseViewComponent} from "../../components/base-view/base-view.component";

@Component({
  selector: 'login-view',
  standalone: true,
  templateUrl: './login-view.component.html',
  imports: [
    BaseViewComponent
  ],
  styleUrl: './login-view.component.sass'
})
export class LoginViewComponent {}


