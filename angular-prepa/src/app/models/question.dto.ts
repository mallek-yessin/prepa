export interface AnswerDTO {
  optionKey: string;
  optionValue: string;
  correct: boolean;
}

export interface QuestionDTO {
  chap: string;
  question: string;       // correspond à dto.getQuestion() dans Spring
  answers: AnswerDTO[];
}

export interface Answer {
  id?: number;
  optionKey: string;
  optionValue: string;
  correct: boolean;
}

export interface Question {
  id?: number;
  question: string;
  chap: string;
  answers: Answer[];
}
