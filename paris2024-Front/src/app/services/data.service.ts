import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService {
  private readonly API_URL = 'http://localhost:8080/';
  constructor(private http: HttpClient) {}
  public makeHttpRequest<T>(
    method: 'get' | 'post' | 'put' | 'delete',
    path: string,
    body?: any,
    params?: HttpParams,
    headers?: HttpHeaders
  ): Observable<T> {
    const url = `${this.API_URL}${path}`;
    switch (method) {
      case 'get':
        return this.http.get<T>(url, { params, headers });
      case 'post':
        return this.http.post<T>(url, body, { headers });
      case 'put':
        return this.http.put<T>(url, body, { headers });
      case 'delete':
        return this.http.delete<T>(url, { params, headers });
      default:
        throw new Error('Invalid HTTP method');
    }
  }
}
