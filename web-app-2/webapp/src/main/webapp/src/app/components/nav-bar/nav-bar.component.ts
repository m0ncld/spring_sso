import {Component, inject} from '@angular/core';
import {Router} from "@angular/router";
import {NavBarRoute, NavBarRoutingService} from "./nav-bar-routing.service";
import {CommonModule} from "@angular/common";
import {AuthService} from "../../service/auth/auth.service";
import {User} from "../../service/auth/user.dto";

@Component({
  selector: 'nav-bar',
  standalone: true,
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss',
  imports: [CommonModule],
  providers: [NavBarRoutingService]
})
export class NavBarComponent {

  routing: NavBarRoute[] = [];

  user: User | null;

  constructor(private readonly router: Router,
              private readonly routingService: NavBarRoutingService,
              private readonly authService: AuthService
  ) {
    this.user = this.authService.user;
  }

  ngOnInit() {
    this.routing = this.user ? this.routingService.getRouting(this.user) : [];
    this.user = this.authService.user;
    this.authService.userObservable.subscribe(user => {
      this.user = user
      this.routing = this.routingService.getRouting(this.user);
    });
  }

  changeRoute(path: String) {
    this.router.navigate([path]);
  }

}


