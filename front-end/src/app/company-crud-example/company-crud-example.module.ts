import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyCrudExampleRoutingModule } from './company-crud-example-routing.module';
import { ListCompanyComponent } from './list-company/list-company.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from '@angular/common/http';
import { AddCompanyComponent } from './add-company/add-company.component';
import { EditCompanyComponent } from './edit-company/edit-company.component';
import { CompanyService } from '../services/user/company.service';


@NgModule({
  declarations: [ListCompanyComponent, AddCompanyComponent, EditCompanyComponent],
  imports: [
    CommonModule,ReactiveFormsModule,
    CompanyCrudExampleRoutingModule,HttpClientModule  ,FormsModule,NgbModule
  ],
  providers: [CompanyService ]
})
export class CompanyCrudExampleModule { }
