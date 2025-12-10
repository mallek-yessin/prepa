package com.example.prepa1.dto;

import java.util.List;

public class qQuestionDTO {
    private String question;  // LaTeX de la question
    private String chap;
    private List<AnswerDTO> answers;

    public String getChap() {
		return chap;
	}
	public void setChap(String chap) {
		this.chap = chap;
	}

	

    // getters & setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public List<AnswerDTO> getAnswers() { return answers; }
    public void setAnswers(List<AnswerDTO> answers) { this.answers = answers; }

   /* public static class AnswerDTO {
        private String optionKey;
        private String optionValue;
        private boolean correct;

        // getters & setters
        public String getOptionKey() { return optionKey; }
        public void setOptionKey(String optionKey) { this.optionKey = optionKey; }

        public String getOptionValue() { return optionValue; }
        public void setOptionValue(String optionValue) { this.optionValue = optionValue; }

        public boolean isCorrect() { return correct; }
        public void setCorrect(boolean correct) { this.correct = correct; }
    }*/
}
