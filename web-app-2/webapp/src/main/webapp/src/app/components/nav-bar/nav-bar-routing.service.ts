import {Injectable} from "@angular/core";
import {Route, Router} from "@angular/router";
import {User} from "../../service/auth/user.dto";

@Injectable()
export class NavBarRoutingService {

  constructor(private readonly router: Router) {
  }

  private readonly routes =  [
    new NavBarRoute('Home', '/', false),
    new NavBarRoute('My lists', '/app/lists/user', false),
    new NavBarRoute('Add list', '/app/lists/add', false),
    new NavBarRoute('Browse lists', '/app/lists/all', false),
  ]

  getRouting(user: User): NavBarRoute[] {
    const routes: NavBarRoute[] = []
    this.createRouting(this.router.config, routes, user);
    console.log('routes', routes);
    return [...routes];
  }

  private createRouting(routes: Route[], navBarRoutes: NavBarRoute[], user: User, path: string = '') {
    const currentUrl = this.router.url;
    const basePath = path + '/'
    routes.forEach(route => {
      const routePath = basePath + route.path;
      if (this.shouldAddRoute(route, user)) {
        const title = route.data!['title'];
        const active = routePath === currentUrl;
        navBarRoutes.push(new NavBarRoute(title, routePath, active));
      }
      const length = route?.children?.length ?? 0;
      if (length != 0) {
        this.createRouting(route.children!, navBarRoutes, user, routePath);
      }
    })
  }

  private shouldAddRoute(route: Route, user: User): boolean {
    if (!route.data) {
      return false;
    }
    if (route.data['inMenue'] !== true) {
      return false;
    }
    if (route.data['role'] == null) {
      return true;
    }

    return !!user.roles?.includes(route.data['role']);
  }
}

export class NavBarRoute {
  constructor(public readonly title: string,
              public readonly path: string,
              public active: boolean = false) {
  }
}
