package com.example.prepa1.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String latex;   // example: "\\int_0^{+∞} x^2 e^{-x} dx = 2"
    
    
    private String chap;
   
   
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Answer> answers;


    // getters + setters

	public String getChap() {
		return chap;
	}

	public void setChap(String chap) {
		this.chap = chap;
	}

	public Question() {
		super();
	}

	public Question(String question) {
		super();
		this.latex = question;
	}
	
	public Question(Long id, String question) {
		super();
		this.id = id;
		this.latex = question;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return latex;
	}

	public void setQuestion(String question) {
		this.latex = question;
	}
	public List<Answer> getAnswers() {
	    return answers;
	}

	public void setAnswers(List<Answer> answers) {
	    this.answers = answers;
	}
	

}
