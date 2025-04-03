import { NgModule } from '@angular/core';
import { CommonModule, DecimalPipe } from '@angular/common';
import { BasicTableComponent } from './basic-table/basic-table.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from  '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CountryService } from './country.service';
import { DataTableComponent } from './data-table/data-table.component';
import { NgbdSortableHeader } from './sortable.directive';

const routes: Routes = [
  { path: 'basic-table', component: BasicTableComponent },
  { path: 'data-table', component: DataTableComponent },
]

@NgModule({
  declarations: [BasicTableComponent, DataTableComponent,NgbdSortableHeader],
  imports: [
    CommonModule,ReactiveFormsModule,
    RouterModule.forChild(routes),HttpClientModule  ,FormsModule,NgbModule
  ]

})
export class TablesModule { }
