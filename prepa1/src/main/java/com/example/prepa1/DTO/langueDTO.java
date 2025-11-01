// langueDTO.java
package com.example.prepa1.DTO;
public class langueDTO {
    private String langue;
    private questionDTO question;
    private reponseDTO reponse;
    private correctanswerDTO correctAnswer;
    public langueDTO(String langue, questionDTO question, reponseDTO reponse, correctanswerDTO correctAnswer) {
        this.langue = langue;
        this.question = question;
        this.reponse = reponse;
        this.correctAnswer = correctAnswer;
    }
    public String getLangue() { return langue; }
    public questionDTO getQuestion() { return question; }
    public reponseDTO getReponse() { return reponse; }
    public correctanswerDTO getCorrectAnswer() { return correctAnswer; }
}
