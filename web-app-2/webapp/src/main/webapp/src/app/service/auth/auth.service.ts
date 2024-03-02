import {Injectable, OnDestroy} from '@angular/core';

import {HttpClient} from "@angular/common/http";
import {User} from "./user.dto";
import {Observable, Subscriber, Subscription} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AuthService implements OnDestroy {
  user: User | null = null;
  authRestSubscription: Subscription;
  private subscriber: Subscriber<User> | null = null;
  private readonly userObservableValue: Observable<User> = new Observable((subscriber) => {this.subscriber = subscriber});

  redirectUrl: string | null = null;

  constructor(private http: HttpClient) {
    this.authRestSubscription = this.http.get('/api/v1/rest/auth')
      .subscribe(data => {
        this.user = data as User;
        if (this.subscriber != null) {
          this.subscriber.next(this.user);
        }
      });
  }

  /**
   * Observable of user state
   */
  get userObservable(): Observable<User> {
    return this.userObservableValue;
  }

  ngOnDestroy() {
    // Unsuscribe on destroy
    this.authRestSubscription.unsubscribe();
    this.subscriber?.unsubscribe()
  }
}
