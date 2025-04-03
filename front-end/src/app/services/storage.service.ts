import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { User } from '../models/user/User';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
 
  static permission: string = " ";
  static user: string = "";
  constructor(public cookieService: CookieService) {
  }
  saveInSessionStorage(key: string, value) {
    sessionStorage.setItem(key, JSON.stringify(value));
  }
  saveInCookieStorage(key: string, value) {
    this.cookieService.set(key, JSON.stringify(value));
  }
  getFromCookieOrSession(key: string) {
    return sessionStorage.getItem(key) || this.cookieService.get(key);
  }
  getuserfromcookieorsession(): User {
    return JSON.parse(sessionStorage.getItem('user')) || this.cookieService.get('user');
  }

  clearSession() {
    sessionStorage.clear();
    this.cookieService.deleteAll();
  }
}
