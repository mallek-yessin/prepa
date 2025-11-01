// reponseDTO.java
package com.example.prepa1.DTO;
import java.util.Map;
public class reponseDTO {
    private Map<String, Map<String, String>> responses;
    public reponseDTO(Map<String, Map<String, String>> responses) { this.responses = responses; }
    public Map<String, Map<String, String>> getResponses() { return responses; }
    public void setResponses(Map<String, Map<String, String>> responses) { this.responses = responses; }
}
