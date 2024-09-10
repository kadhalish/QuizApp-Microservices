package com.example.quizService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizService.model.QuestionWrapper;
import com.example.quizService.model.QuizDTO;
import com.example.quizService.model.Response;
import com.example.quizService.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDto) {
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumberOfQuestions(), quizDto.getTitle());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
		return quizService.getQuizQuestions(id);
	}

	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitAnswer(@PathVariable int id, @RequestBody List<Response> responses) {
		return quizService.calculateScore(id, responses);
	}

}
