import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppConstants } from 'src/app/common/app.constants';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hide: boolean = true;
  reactiveForm: FormGroup;
  username: string;
  password: string;
  remember: boolean = false;
  errorMessage = '';
  message='';
  googleURL = AppConstants.GOOGLE_AUTH_URL;
  facebookURL = AppConstants.FACEBOOK_AUTH_URL;
  githubURL = AppConstants.GITHUB_AUTH_URL;
  linkedinURL = AppConstants.LINKEDIN_AUTH_URL;

  constructor(private storageService:StorageService,private route: ActivatedRoute, private fb: FormBuilder, private router: Router, private auth: AuthenticationService) {
    this.reactiveForm = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      remember: ['']
    });

  }

  goTo(url) {
    this.storageService.clearSession();
    window.location.href = url;
  }
  ngOnInit(): void {
    const token: string = this.route.snapshot.queryParamMap.get('token');
    const error: string = this.route.snapshot.queryParamMap.get('error');
    if (error) {
      this.errorMessage = error;
    }
    if (token) {
      this.auth.isUserLogedSocialMedia(token);
      this.router.navigate(['dashboard/']);
    }
    this.message = this.route.snapshot.params['message'];

  }


  get controls(): { [p: string]: AbstractControl } {
    return this.reactiveForm.controls;
  }

  login() {
    this.message = '';
    this.storageService.clearSession();
    //  this.auth.login(this.username,this.password,this.remember).subscribe(data=>console.log(data));
    this.auth.login(this.username, this.password, this.remember).subscribe(
      data => {
        if (data) {
          this.router.navigate(['dashboard/']);
        }
        else {
          this.errorMessage = 'Username or password is incorrect';
        }

      },
      error => {
        this.errorMessage = 'Username or password is incorrect';
      }
    );
  }

}
