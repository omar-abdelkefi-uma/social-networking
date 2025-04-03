import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {
  message: string;
  reactiveForm: FormGroup;
  constructor(private storageService: StorageService, private fb: FormBuilder, private router: Router, private auth: AuthenticationService) {
    this.reactiveForm = this.fb.group({
      username: ['', [Validators.required]],
    });

  }

  ngOnInit(): void {
  }
  resetPass() {
    this.storageService.clearSession();
    
    this.auth.resetPassword(this.reactiveForm.get('username').value).subscribe(
      (response) => {
        this.message = "an email has been sent, please check your email"
        this.router.navigate(['user/login/' + this.message]);

      });

  }
}
