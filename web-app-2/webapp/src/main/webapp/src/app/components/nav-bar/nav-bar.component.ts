import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NavBarRoute, NavBarRoutingService} from "./nav-bar-routing.service";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'nav-bar',
  standalone: true,
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.sass',
  imports: [CommonModule],
  providers: [NavBarRoutingService]
})
export class NavBarComponent {

  routing: NavBarRoute[] = [];

  constructor(private readonly router: Router,
              private readonly routingService: NavBarRoutingService) {}

  ngOnInit() {
    const currentUrl = this.router.url;
    this.routing = this.routingService.getRouting().map(route => {
      route.active = route?.path === currentUrl;
      return route;
    })
  }

}


