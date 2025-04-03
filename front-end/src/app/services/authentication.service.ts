import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
// you have to import HttpClientModule to get httpclient
import { HttpClient, HttpClientModule, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, map, tap } from 'rxjs/operators';
import { StorageService } from './storage.service';
import { UserService } from './user/user.service';
import { User } from '../models/user/User';
import { CookieService } from 'ngx-cookie-service';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {



  private actionUrl: string;
  public token: string;
  private user: User;
  perssssss: any;
  public id: number;
  private headers = new Headers();
  private options;
  expirationPeriodCookies: any = 0;
  private currentUserSubject: BehaviorSubject<User>;
  public currentPerson: Observable<User>;
  constructor(private storageservice: StorageService, private userservice: UserService, private cookieService: CookieService, private http: HttpClient, private _router: Router) {
    this.currentUserSubject = new BehaviorSubject<User>(this.storageservice.getuserfromcookieorsession());
    this.currentPerson = this.currentUserSubject.asObservable();
  }
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  signUp(signUpRequest: { displayName: any; email: any; password: string; matchingPassword: string; }) {
    return this.http.post('http://localhost:8080/api/auth/signup', signUpRequest);
  }
  //Reading the full response { observe: 'response' }
  login(email, password, rememberMe): Observable<any> {
    return this.http.post('http://localhost:8080/api/auth/signin', { email: email, password: password })
      .pipe(
        map((response: any) => {
          this.saveLoggedUser(response.accessToken, response, rememberMe);
          return true;
        },
          err => {
            return false;
          })
      )
  }
  private saveLoggedUser(token: string, response: any, rememberMe: any) {
    this.token = token.toString();
    //cast object to user class
    this.user = response.user as User;
    this.currentUserSubject.next(this.user);
    this.saveInCookieOrInSession(this.token, rememberMe, this.user);
    //get period expiration from backend
    this.getExpirationPeriodCookies().subscribe((data) => {
      this.expirationPeriodCookies = +data;
    });
  }

  isUserLogedSocialMedia(token: string) {
    if (this.storageservice.getFromCookieOrSession('token')) {
      this.storageservice.getuserfromcookieorsession();
    }
    else if (token) {
      this.storageservice.saveInSessionStorage('token', token);
      this.userservice.getCurrentUser().subscribe(
        data => {
          this.storageservice.saveInSessionStorage('user', data);
        }
      );
    }
  }

  errorHandler(error: any): void {
  }
  logout() {
    // remove User from local storage to log User out
    this.cookieService.deleteAll();
    sessionStorage.clear();
    this.currentUserSubject.next(null);
  }
  resetPassword(username: string) {
    return this.http.post('http://localhost:8080/forgot-password', { username: username }, { responseType: 'text' })
      .pipe(map(data => {

        console.log(data)
      }));
  }
  confirmNewPass(token: string, newPassword: string): any {
    const data = { password: newPassword, token: token };

    return this.http.put('http://localhost:8080/new-password', data)
  }
  saveInCookieOrInSession(jwtToken: string, rememberMe: boolean, user: User) {
    if (rememberMe) {
      //this.expirationPeriodCookies in day ## 0.000694444 day (frombackend)  = expiration Period 1 min
      this.cookieService.set('token', jwtToken, this.expirationPeriodCookies);
      this.cookieService.set('user', JSON.stringify(user));
    } else {
      sessionStorage.setItem('token', jwtToken);
      sessionStorage.setItem('user', JSON.stringify(user));
    }
  }
  getExpirationPeriodCookies() {
    return this.http.get('http://localhost:8080/api/auth/expirationPeriodCookies');
  }
}
