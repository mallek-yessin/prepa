package com.example.prepa1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prepa1.entity.LatexEntity;
import com.example.prepa1.repository.LatexRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class quizController {
	
	@Autowired
	LatexRepository latexRepository;
	
	
	@PostMapping("saveQuestion")
	public void postLatex(@RequestBody LatexEntity question){
		latexRepository.save(question);
	}
	
	@GetMapping("allQuestions")
	public  List<LatexEntity> getAll(){
		return latexRepository.findAll();
	}
	
	@GetMapping("questions")
	public String getUserById(){
		return """
				{"python.json":[
    {
        "1": "What Will Be The Output Of the Following Code ?\\na = \\"p\\" * 3\\nprint(a)",
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
		
	}

}
