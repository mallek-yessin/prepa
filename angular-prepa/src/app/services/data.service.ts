// services/item.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  constructor(private http: HttpClient) { }

  getAllQuestions(): Observable<any> {
    return this.http.get(API_URL+"allQuestions");
  }

  saveQuestion(request: any):Observable<any> {
    return this.http.post<any>(API_URL + "saveQuestion", request);
  }
}