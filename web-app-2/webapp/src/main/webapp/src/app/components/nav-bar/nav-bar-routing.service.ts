import {Inject} from "@angular/core";

@Inject({})
export class NavBarRoutingService {

    getRouting(): NavBarRoute[] {
      return [
        new NavBarRoute('Home', '/', false),
        new NavBarRoute('My lists', '/app/lists/user', false),
        new NavBarRoute('Add list', '/app/lists/add', false),
        new NavBarRoute('Browse lists', '/app/lists/all', false),
      ]
    }
}

export class NavBarRoute {
  constructor(public readonly title: string,
              public readonly path: string,
              public active: boolean = false) {
  }
}
