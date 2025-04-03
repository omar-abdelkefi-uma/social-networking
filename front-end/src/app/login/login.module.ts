import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewPasswordComponent } from './new-password/new-password.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
//§important
import { LoginRoutingModule } from './login-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthenticationService } from '../services/authentication.service';
import { UserService } from '../services/user/user.service';
import { InterceptorService } from '../services/interceptor.service';
import { AuthGuardService } from '../services/auth-guard.service';
import { SignUpComponent } from './sign-up/sign-up.component';







@NgModule({
  declarations: [NewPasswordComponent, ForgetPasswordComponent, LoginComponent, SignUpComponent],
  imports: [
    CommonModule, FormsModule, RouterModule, LoginRoutingModule, HttpClientModule, ReactiveFormsModule,
  ], providers: [
    AuthenticationService,
    UserService,
    {
      provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true
    },
    AuthenticationService, AuthGuardService
    // other services
  ],
})
export class LoginModule { }
