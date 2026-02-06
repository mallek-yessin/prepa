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

  translations = {
  FR: {
    questions: "Questions",
    addQuestion: "Ajouter une question +",
    id: "ID",
    question: "Question",
    chapter: "Chapitre",
    answers: "Réponses",
    actions: "Actions",
    correct: "Correct",
    remove: "Supprimer",
    addAnswer: "Ajouter une réponse",
    save: "Enregistrer",
    update: "Mettre à jour",
    cancel: "Annuler",
    edit: "Modifier",
  },

  EN: {
    questions: "Questions",
    addQuestion: "Add question +",
    id: "ID",
    question: "Question",
    chapter: "Chapter",
    answers: "Answers",
    actions: "Actions",
    correct: "Correct",
    remove: "Remove",
    addAnswer: "Add answer",
    save: "Save",
    update: "Update",
    cancel: "Cancel",
    edit: "Edit",
  }
};

  
  

  questions: Question[] = [];
  filteredQuestions: Question[] = [];
  chapters: string[] = [];
  selectedChapter = '';
  searchText = '';
  showForm = false;
  currentQuestion: Question = { question: '', chap: '', answers: [] };
   currentLang = 'FR';
   currentPage = 1;
  itemsPerPage = 5;
  pageSizeOptions = [5, 10, 25];


  constructor(private questionService: QuestionService) {}

  ngOnInit() {
    this.loadChapters();
    this.loadAllQuestions();
  }

  /////

 t(key: string) {
  return (this.translations as any)[this.currentLang][key];
}



setLang(lang: string) {
  this.currentLang = lang;
}


  loadChapters() {
    this.questionService.getChapters().subscribe(chaps => this.chapters = chaps);
  }

  loadAllQuestions() {
  this.questionService.getAllQuestions().subscribe((data: Question[]) => {
    this.questions = data;
  });
}

  /*loadQuestions(chap: string) {
    this.selectedChapter = chap;
    this.questionService.getAllQuestions().subscribe(data => {
      const questionsObj = data as { [key: string]: Question[] };
      console.log(questionsObj)
      this.questions = questionsObj[chap];
      console.log(this.questions)
     // this.filterQuestions();
    });
  }*/

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
      this.questionService.deleteQuestion(id).subscribe(() => this.loadAllQuestions());
    }
  }






 changeItemsPerPage(newSize: number) {
    this.itemsPerPage = newSize;
    this.currentPage = 1; // Retour à la première page
  }
  
  //pgination
    get paginatedItems() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    return this.questions.slice(startIndex, startIndex + this.itemsPerPage);
  }

    // Getter pour le nombre total de pages
  get totalPages() {
    return Math.ceil(this.questions.length / this.itemsPerPage);
  }



  // Aller à une page spécifique
  goToPage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }

  // Générer la liste des numéros de page à afficher
  getPageNumbers(): number[] {
    const pages = [];
    const maxVisiblePages = 5; // Nombre maximum de pages visibles dans la pagination
    
    let startPage = Math.max(1, this.currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = startPage + maxVisiblePages - 1;
    
    if (endPage > this.totalPages) {
      endPage = this.totalPages;
      startPage = Math.max(1, endPage - maxVisiblePages + 1);
    }
    
    for (let i = startPage; i <= endPage; i++) {
      pages.push(i);
    }
    
    return pages;
  }


     // Méthode pour calculer la valeur minimale
  getDisplayRangeEnd(currentPage: number, itemsPerPage: number, totalItems: number): number {
    return Math.min(currentPage * itemsPerPage, totalItems);
  }

  // Méthode pour calculer le début de la plage
  getDisplayRangeStart(currentPage: number, itemsPerPage: number): number {
    return (currentPage - 1) * itemsPerPage + 1;
  }
}
