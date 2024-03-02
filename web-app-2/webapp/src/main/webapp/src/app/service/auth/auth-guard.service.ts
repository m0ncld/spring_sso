import {inject} from '@angular/core';

import {AuthService} from './auth.service';

export const authGuard = (next: any, state: any) => {
  const authService = inject(AuthService);

  const nextRole = next?.data?.role as string;

  if (nextRole == null) {
    return true;
  }

  if (authService.user?.authenticated !== true) {
    // User is not logged
    window.location.href = 'oauth2/authorization/oidc';
    return false;
  }

  if (!authService.user?.roles?.includes(nextRole)) {
    // User does not have access to view
    return false;
  }

  // Redirect to the login page
  return true;
};
