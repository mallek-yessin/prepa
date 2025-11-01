package com.example.prepa1.DTO;

import java.util.Map;

public class questionDTO {
    private Map<String, String> questions;
    public questionDTO(Map<String, String> questions) { this.questions = questions; }
    public Map<String, String> getQuestions() { return questions; }
    public void setQuestions(Map<String, String> questions) { this.questions = questions; }
}
