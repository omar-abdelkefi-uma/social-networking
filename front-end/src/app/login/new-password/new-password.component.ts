import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.scss']
})
export class NewPasswordComponent implements OnInit {
  matchingPassword: string = "";
  errorMessage;
  confirmpassword: string = "";
  token: string;
  hide: boolean = true;
  hideconfirmpassword: boolean = true;
  reactiveForm: FormGroup;
  password: string = "";

  constructor(private storageService:StorageService,private fb: FormBuilder, private route: ActivatedRoute, private authService: AuthenticationService, private router: Router) {

    this.reactiveForm = this.fb.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
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

  changePassword() {
    this.storageService.clearSession();
    this.authService.confirmNewPass(this.token, this.matchingPassword).subscribe(
      data => {
        this.router.navigate(['user/login/' + "your password has changed successfully"]);
      }
    );
  }

}
