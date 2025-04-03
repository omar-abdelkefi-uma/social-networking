import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpEvent, HttpResponse, HttpRequest } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { StorageService } from './storage.service';
import { EMPTY } from 'rxjs';
import { LoadingService } from './loading.service';
import { catchError, finalize, map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {
  constructor(private loadingService: LoadingService, private storageservice: StorageService, private authenticationService: AuthenticationService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler) {

    if (this.storageservice.getFromCookieOrSession("token")) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + this.storageservice.getFromCookieOrSession("token")
        }
      })
    }
    this.loadingService.setLoading(true, req.url);
    return next.handle(req)
      .pipe(catchError((err) => {
        this.loadingService.setLoading(false, req.url);
        return err;
      }))
      .pipe(map<HttpEvent<any>, any>((evt: HttpEvent<any>) => {
        if (evt instanceof HttpResponse) {
          this.loadingService.setLoading(false, req.url);
        }
        return evt;
      }));
  }
}