import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Question, QuestionDTO } from '../models/question.dto';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {


  private baseUrl =environment.baseUrl//'/api';// 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  saveQuestion(dto: QuestionDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/saveq`, dto);
  }

getAllQuestions(): Observable<any> {
    return this.http.get(`${this.baseUrl}/questions`);
  }
   // Get all questions by chapter
  getQuestions(chap: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/questions/${chap}`);
  }

  // Get all chapters
  getChapters(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/chapters`);
  }

  // Create question
  createQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(`${this.baseUrl}/saveq`, question);
  }

  // Update question
  updateQuestion(id: number, question: Question): Observable<Question> {
    return this.http.put<Question>(`${this.baseUrl}/questionUpdate/${id}`, question);
  }

  // Delete question
  deleteQuestion(id: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/questionDelete/${id}`, { responseType: 'text' });
  }
}
