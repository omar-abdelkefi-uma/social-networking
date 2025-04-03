import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CompanyService } from 'src/app/services/user/company.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.scss']
})
export class AddCompanyComponent implements OnInit {
  companygroup: FormGroup;
  constructor(private fb: FormBuilder, private companyService: CompanyService, private router: Router) { 
    this.companygroup = this.fb.group({
      companyControl: ['', [Validators.required]],
      descriptionControl: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

}
