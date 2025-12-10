import { Component } from '@angular/core';
import { QuestionService } from '../../services/data.service';
import { QuestionDTO, AnswerDTO } from '../../models/question.dto';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-question',
  standalone: true,
  templateUrl: './add-question.component.html',
  imports: [FormsModule,CommonModule] 
})
export class AddQuestionComponent {

  chapitre='';
  questionText = '';

    answers = [
    { optionKey: 'a', optionValue: '', correct: false },
    { optionKey: 'b', optionValue: '', correct: false },
    { optionKey: 'c', optionValue: '', correct: false },
    { optionKey: 'd', optionValue: '', correct: false }
  ];

  constructor(private questionService: QuestionService) {}

  save() {
    const dto: QuestionDTO = {
      chap:this.chapitre,
      question: this.questionText,
      answers: this.answers
    };

    this.questionService.saveQuestion(dto).subscribe({
      next: res => console.log('saved', res),
      error: err => console.error(err)
    });
  }
}
