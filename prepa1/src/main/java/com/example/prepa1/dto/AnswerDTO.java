package com.example.prepa1.dto;

public class AnswerDTO {
	private Long id;  // null = nouvelle réponse
	private String optionKey;
	private String optionValue;
	private boolean correct;
    
    public String getOptionKey() { return optionKey; }
    public void setOptionKey(String optionKey) { this.optionKey = optionKey; }

    public String getOptionValue() { return optionValue; }
    public void setOptionValue(String optionValue) { this.optionValue = optionValue; }

    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
