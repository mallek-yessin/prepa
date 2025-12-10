package com.example.prepa1.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.prepa1.dto.AnswerDTO;
import com.example.prepa1.dto.qQuestionDTO;
import com.example.prepa1.entity.Answer;
import com.example.prepa1.entity.Question;

import com.example.prepa1.repository.LatexRepository;

@Service
public class QuestionService {

    @Autowired
    private LatexRepository repo;
    
    


    public Question updateQuestion(Long id, qQuestionDTO dto) {
        Question q = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question introuvable"));

        // Update question fields
        q.setQuestion(dto.getQuestion());
        q.setChap(dto.getChap());

        // Clear old answers if needed
        q.getAnswers().clear();

        // Add updated answers
        for (AnswerDTO a : dto.getAnswers()) {
            Answer answer = new Answer();
            answer.setId(a.getId());
            answer.setOptionKey(a.getOptionKey());
            answer.setOptionValue(a.getOptionValue());
            answer.setCorrect(a.isCorrect());
            answer.setQuestion(q);

            q.getAnswers().add(answer);
        }

        return repo.save(q);
    }
    
  /*  public Question updateQuestion(Long id, qQuestionDTO dto) {

        Question question = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // Mise à jour des champs simples
        question.setQuestion(dto.getQuestion());
        question.setChap(dto.getChap());

        // Liste des anciennes réponses
        Map<Long, Answer> existingAnswers = question.getAnswers()
                .stream().collect(Collectors.toMap(Answer::getId, a -> a));

        // Nouvelle liste
        List<Answer> updatedAnswers = dto.getAnswers().stream().map(a -> {
            Answer answer;
            if (a.getId() != null && existingAnswers.containsKey(a.getId())) {
                // Update d'une réponse existante
                answer = existingAnswers.get(a.getId());
            } else {
                // Nouvelle réponse
                answer = new Answer();
                answer.setQuestion(question);
            }

            answer.setOptionKey(a.getOptionKey());
            answer.setOptionValue(a.getOptionValue());
            answer.setCorrect(a.isCorrect());

            return answer;
        }).collect(Collectors.toList());


        // Suppression des anciennes réponses non présentes dans la liste
        question.getAnswers().clear();
        question.getAnswers().addAll(updatedAnswers);

        return repo.save(question);
    }*/
    
    
    public void deleteQuestion(Long id) {
    	repo.deleteById(id);
    }

    
    
   
    
    public AllDataDTO getFormatted(String chap) {

        List<Question> all = repo.findByChap(chap);

        // 1) Latex map
        Map<String, String> latexMap = new java.util.LinkedHashMap<>();

        for (int i = 0; i < all.size(); i++) {
            String key = String.valueOf(i + 1);
            String value = String.valueOf(all.get(i).getQuestion());
            latexMap.put(key, value);
        }



        // 2) Answers map
        Map<String, Map<String, String>> answersMap = new LinkedHashMap<>();

        for (int i = 0; i < all.size(); i++) {

            Question q = all.get(i);

            // map des réponses a/b/c/d
            Map<String, String> answerMap = new LinkedHashMap<>();

            for (Answer a : q.getAnswers()) {
                answerMap.put(a.getOptionKey(), a.getOptionValue());
            }


            // clé principale 1,2,3,...
            answersMap.put(String.valueOf(i + 1), answerMap);
        }


        // 3) Correct answers map
        Map<String, String> correctMap = new LinkedHashMap<>();

        for (int i = 0; i < all.size(); i++) {

            Question q = all.get(i);

            String correctValue = null;

            for (Answer a : q.getAnswers()) {
                if (a.isCorrect()) {
                    correctValue = a.getOptionValue();
                    break; // on arrête dès qu'on trouve le correct
                }
            }

            correctMap.put(String.valueOf(i + 1), correctValue);
        }


        // return all 3 maps together
        return new AllDataDTO(latexMap, answersMap, correctMap);
    }


    // DTO contenant les trois JSON
    public static class AllDataDTO {
        public Map<String, String> latex;
        public Map<String, Map<String, String>> answers;
        public Map<String, String> correct;

        public AllDataDTO(Map<String, String> latex,
                          Map<String, Map<String, String>> answers,
                          Map<String, String> correct) {
            this.latex = latex;
            this.answers = answers;
            this.correct = correct;
        }
    }

}
