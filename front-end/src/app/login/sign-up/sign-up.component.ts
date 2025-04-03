import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SocialProvider } from 'src/app/models/user/social-provider';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  matchingPassword: string = "";
  errorMessage;
  token: string;
  reactiveForm: FormGroup;
  password: string = "";
  displayName;
  username;


  constructor(private storageService:StorageService,private fb: FormBuilder, private route: ActivatedRoute, private authService: AuthenticationService, private router: Router) {

    this.reactiveForm = this.fb.group({
      displayName: ['', [Validators.required]],
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, , Validators.minLength(6)]],
      matchingPassword: ['', [Validators.required]]
    }, { validators: this.passwordMatchValidator });

  }

  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password').value;
    const matchingPassword = form.get('matchingPassword').value;

    if (password !== matchingPassword) {
      form.get('matchingPassword').setErrors({ passwordsMismatch: true });
    } else {
      if (form.get('matchingPassword').hasError('passwordsMismatch')) {
        form.get('matchingPassword').setErrors({ passwordsMismatch: null }); // Clear the error if they match

      }
    }
  }
  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.token = params['token'];
    });
  }

  signUp() {
    this.storageService.clearSession();
    const signUpRequest = {
      displayName: this.displayName, email: this.username, password: this.password, matchingPassword
        : this.matchingPassword, socialProvider: SocialProvider.LOCAL // Directly using the enum value

    }
    this.authService.signUp(signUpRequest).subscribe(
      data => {
        this.router.navigate(['user/login/' + 'your account has been created successfully']);
      }
    );
  }


}
