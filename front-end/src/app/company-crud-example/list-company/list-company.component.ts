import { DecimalPipe } from '@angular/common';
import { Component, OnInit, QueryList, ViewChildren } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from 'src/app/tables/country';
import { CountryService } from 'src/app/tables/country.service';
import { NgbdSortableHeader, SortEvent } from 'src/app/tables/sortable.directive';
import { AsyncPipe } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-list-company',
  templateUrl: './list-company.component.html',
  styleUrls: ['./list-company.component.scss'],
  providers: [CountryService, DecimalPipe]
})
export class ListCompanyComponent {

  countries$: Observable<Country[]>;
  total$: Observable<number>;

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  constructor(public service: CountryService,private router: Router,) {
    this.countries$ = service.countries$;
    this.total$ = service.total$;
  }

  onSort({column, direction}: SortEvent) {

    // resetting other headers
    this.headers.forEach(header => {
      if (header.sortable !== column) {
        header.direction = '';
      }
    });

    this.service.sortColumn = column;
    this.service.sortDirection = direction;
  }

  addCompany(){
    this.router.navigate(['/company/add']);
  }
}
