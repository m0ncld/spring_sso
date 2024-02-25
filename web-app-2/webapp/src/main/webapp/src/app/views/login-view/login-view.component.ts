import {Component} from '@angular/core';
import {BaseViewComponent} from "../../components/base-view/base-view.component";

@Component({
  selector: 'login-view',
  standalone: true,
  templateUrl: './login-view.component.html',
  imports: [
    BaseViewComponent
  ]
})
export class LoginViewComponent {}


