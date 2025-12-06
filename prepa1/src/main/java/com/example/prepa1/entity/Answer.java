package com.example.prepa1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionKey;      // a, b, c, d
    private String optionValue;    // texte
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    

	

    public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer() {}

    public Answer(String optionKey, String optionValue, boolean correct) {
        this.optionKey = optionKey;
        this.optionValue = optionValue;
        this.correct = correct;
    }

    public Answer(Long id, String optionKey, String optionValue, boolean correct) {
        this.id = id;
        this.optionKey = optionKey;
        this.optionValue = optionValue;
        this.correct = correct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionKey() {     
        return optionKey;
    }

    public void setOptionKey(String optionKey) { 
        this.optionKey = optionKey;
    }

    public String getOptionValue() {   
        return optionValue;
    }

    public void setOptionValue(String optionValue) {   
        this.optionValue = optionValue;
    }

    public boolean isCorrect() {       
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
   
}


