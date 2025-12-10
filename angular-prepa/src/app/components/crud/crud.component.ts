import { Component, OnInit } from '@angular/core';
import { Question } from '../../models/question.dto';
import { QuestionService } from '../../services/data.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';



@Component({
  selector: 'app-crud',
    templateUrl: './crud.component.html',
  styleUrl: './crud.component.css',
  imports: [FormsModule,CommonModule] 

  
})
export class CrudComponent implements OnInit {
  
  

  questions: Question[] = [];
  filteredQuestions: Question[] = [];
  chapters: string[] = [];
  selectedChapter = '';
  searchText = '';
  showForm = false;
  currentQuestion: Question = { question: '', chap: '', answers: [] };

  constructor(private questionService: QuestionService) {}

  ngOnInit() {
    this.loadChapters();
    this.loadAllQuestions();
  }

  loadChapters() {
    this.questionService.getChapters().subscribe(chaps => this.chapters = chaps);
  }

  loadAllQuestions() {
  this.questionService.getAllQuestions().subscribe((data: Question[]) => {
    this.questions = data;
  });
}

  loadQuestions(chap: string) {
    this.selectedChapter = chap;
    this.questionService.getAllQuestions().subscribe(data => {
      const questionsObj = data as { [key: string]: Question[] };
      console.log(questionsObj)
      this.questions = questionsObj[chap];
      console.log(this.questions)
     // this.filterQuestions();
    });
  }

 /* filterQuestions() {
    const text = this.searchText.toLowerCase();
    this.filteredQuestions = this.questions.filter(q =>
      q.latex.toLowerCase().includes(text) || q.chap.toLowerCase().includes(text)
    );
  }*/

  openForm() {
    this.showForm = true;
    this.currentQuestion = { question: '', chap: '', answers: [] };
  }

  closeForm() {
    this.showForm = false;
  }

  addAnswer() {
    if (!this.currentQuestion.answers) this.currentQuestion.answers = [];
    this.currentQuestion.answers.push({ optionKey: '', optionValue: '', correct: false });
  }

  removeAnswer(index: number) {
    this.currentQuestion.answers.splice(index, 1);
  }

  editQuestion(q: Question) {
    this.currentQuestion = JSON.parse(JSON.stringify(q)); // deep copy
    this.showForm = true;
  }

  saveQuestion() {
    if (this.currentQuestion.id) {
      this.questionService.updateQuestion(this.currentQuestion.id, this.currentQuestion).subscribe(() => {
        this.loadAllQuestions();
        this.closeForm();
      });
    } else {
      this.questionService.createQuestion(this.currentQuestion).subscribe(() => {
        this.loadAllQuestions();
        this.closeForm();
      });
    }
  }

  deleteQuestion(id: number) {
    if (confirm('Voulez-vous vraiment supprimer cette question ?')) {
      this.questionService.deleteQuestion(id).subscribe(() => this.loadQuestions(this.selectedChapter));
    }
  }
}
