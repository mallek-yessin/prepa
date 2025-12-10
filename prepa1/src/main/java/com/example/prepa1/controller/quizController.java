package com.example.prepa1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prepa1.dto.qQuestionDTO;
import com.example.prepa1.entity.Answer;
import com.example.prepa1.entity.Question;
import com.example.prepa1.repository.LatexRepository;
import com.example.prepa1.service.QuestionService;

@RestController
@RequestMapping("/api")
public class quizController {
	
	@Autowired
	LatexRepository latexRepository;

	@Autowired
    private QuestionService questionService;
	
	
	@GetMapping("/questions")
	public List<Question> getAllQuestions() {
	    return latexRepository.findAll();
	}
	

	
	@PutMapping("/questionUpdate/{id}")
    public Question updateQuestion(@PathVariable Long id, @RequestBody qQuestionDTO dto) {
        return questionService.updateQuestion(id, dto);
    }
	
	
	@DeleteMapping("/questionDelete/{id}")
	public String deleteQuestion(@PathVariable Long id) {
	    questionService.deleteQuestion(id);
	    return "Question supprimée, réponses supprimées automatiquement";
	}


    @GetMapping("questions/{chap}")
    public Object getAll(@PathVariable String  chap) {
    	Map<String, Object> map = new HashMap<>();
        map.put(chap,List.of(questionService.getFormatted(chap).latex,questionService.getFormatted(chap).answers,questionService.getFormatted(chap).correct) );  // pas besoin de List.of(...)
        
        return map;
    }
    
    @GetMapping("/chapters")
    public List<String> getChapters() {
        return latexRepository.findAll()
                .stream()
                .map(Question::getChap)
                .distinct()
                .toList();
    }

	
    @PostMapping("saveq")
    public Question createQuestion(@RequestBody qQuestionDTO dto) {
        Question question = new Question();
        question.setQuestion(dto.getQuestion());
        question.setChap(dto.getChap());
        
        
        List<Answer> answers = dto.getAnswers().stream().map(a -> {
            Answer answer = new Answer();
            answer.setOptionKey(a.getOptionKey());
            answer.setOptionValue(a.getOptionValue());
            answer.setCorrect(a.isCorrect());
            answer.setQuestion(question); // liaison avec Question
            return answer;
        }).collect(Collectors.toList());

        question.setAnswers(answers);

        return latexRepository.save(question);
    }
	
	
	@PostMapping("saveQuestion")
	public void postLatex(@RequestBody Question question){
		latexRepository.save(question);
	}
	
	
	
	/*@GetMapping("allQuestions")
	public  List<Question> getAll(){
		return latexRepository.findAll();
	}
	
	@GetMapping("questions")
	public String getUserById(){
		return """
				{"python.json":[
    {
        "1": "\\\\int_{0}^{+\\\\infty} x^2 e^{-x}\\\\, dx = 2",
        "2": "\\\\frac{1}{x^2} + \\\\sqrt{3}",
        "3": "\\\\frac{1}{x^2} + \\\\sqrt{3}",
        "4": "Which of the following is a print Function in Python ?",
        "5": "Which function finds out the Variable type in Python ?",
        "6": "Which of the following keyword is used to define a function in Python ?",
        "7": "Which function finds out the Variable type in Python ?",
        "8": "Which of the following keyword is used to define a function in Python ?",
        "9": "Which function finds out the Variable type in Python ?",
        "10": "Which of the following keyword is used to define a function in Python ?"
    },
    {
        "1": {
            "a": "pp",
            "b": "ppp",
            "c": "3p",
            "d": "p3"
        },
        "2": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "3": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        },
        "4": {
            "a": "cout",
            "b": "print",
            "c": "println",
            "d": "stderr"
        },
        "5": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "6": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        },
        "7": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "8": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        },
        "9": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "10": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        }
    },
    {
        "1": "ppp",
        "2": "type",
        "3": "def",
        "4": "print",
        "5": "type",
        "6": "def",
        "7": "type",
        "8": "def",
        "9": "type",
        "10": "def"
    }
]
,
"cpp.json":[
    {
        "1": "Which of the following is not a C++ Header file ?",
        "2": "Which function finds out the Variable type in Python ?",
        "3": "Which of the following keyword is used to define a function in Python ?",
        "4": "Which of the following is a print Function in Python ?",
        "5": "Which function finds out the Variable type in Python ?",
        "6": "Which of the following keyword is used to define a function in Python ?",
        "7": "Which function finds out the Variable type in Python ?",
        "8": "Which of the following keyword is used to define a function in Python ?",
        "9": "Which function finds out the Variable type in Python ?",
        "10": "Which of the following keyword is used to define a function in Python ?"
    },
    {
        "1": {
            "a": "conio.h",
            "b": "cstdlib.h",
            "c": "stdio.h",
            "d": "math.h"
        },
        "2": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "3": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        },
        "4": {
            "a": "cout",
            "b": "print",
            "c": "println",
            "d": "stderr"
        },
        "5": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "6": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        },
        "7": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "8": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        },
        "9": {
            "a": "typedef",
            "b": "typeof",
            "c": "type",
            "d": "find"
        },
        "10": {
            "a": "func",
            "b": "def",
            "c": "void",
            "d": "function"
        }
    },
    {
        "1": "stdio.h",
        "2": "type",
        "3": "def",
        "4": "print",
        "5": "type",
        "6": "def",
        "7": "type",
        "8": "def",
        "9": "type",
        "10": "def"
    }
]
}
				""";
		
	}*/



}