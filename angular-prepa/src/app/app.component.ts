import { CommonModule } from '@angular/common';
import { Component, AfterViewInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { QuestionService } from './services/data.service';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { CrudComponent } from './components/crud/crud.component';

declare var MathJax: any;
declare const window: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [CommonModule, ReactiveFormsModule, FormsModule,AddQuestionComponent,CrudComponent],

})
export class AppComponent {
  
  

}
