import { Injectable } from '@angular/core';
//import
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from 'src/app/models/company';
@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private baseUrl = 'http://localhost:8080/api';
  constructor(private http: HttpClient) { }
  createcompany(company: Company): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/company/add`, company);
  }
  getcompanysList(): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/company/liste`);
  }
  getcompany(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + `/company/${id}`);
  }
  deletecompany(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/company/delete/${id}`);
  }
  updatecompany(company: Company): Observable<Object> {
    return this.http.put(`${this.baseUrl}` + `/company/update`, company);
  }
}
