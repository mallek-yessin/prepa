// correctanswerDTO.java
package com.example.prepa1.DTO;
import java.util.Map;
public class correctanswerDTO {
    private Map<String, String> correctAnswers;
    public correctanswerDTO(Map<String, String> correctAnswers) { this.correctAnswers = correctAnswers; }
    public Map<String, String> getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(Map<String, String> correctAnswers) { this.correctAnswers = correctAnswers; }
}
