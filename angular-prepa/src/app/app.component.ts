import { CommonModule } from '@angular/common';
import { Component, AfterViewInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { QuestionService } from './services/data.service';

declare var MathJax: any;
declare const window: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  styleUrls: ['./app.component.css'] // note le "s" à styleUrls
})
export class AppComponent implements AfterViewInit {
  input = '';   // ton texte latex
  output = '';  // HTML rendu

  constructor(private questionService: QuestionService) {

  }

  ngAfterViewInit() {
    this.renderLatex();
  }

  renderLatex() {
    this.output = this.input;

    // Affichage dans la console
    console.log('input:', this.input);
    console.log('output:', this.output);

    // Demande à MathJax de rendre le LaTeX
    setTimeout(() => {
      if (window['MathJax']) {
        MathJax.typesetPromise().then(() => {
          console.log('MathJax a rendu le LaTeX.');
        }).catch((err: any) => console.error(err));
      }
    }, 0);
  }

  save() {
    const x = {
      "question": this.input
    }
    this.questionService.saveQuestion(x).subscribe({
    next: (data) => {
      // Map your API data to the shape needed by HTML template
      console.log(data);
      
    },
    error: (err) => {
      console.error('Failed :', err);
    }
  });
  }


    display() {
    
    this.questionService.getAllQuestions().subscribe({
    next: (data) => {
      // Map your API data to the shape needed by HTML template
      console.log(data);
      
    },
    error: (err) => {
      console.error('Failed :', err);
    }
  });
  
  }

}
