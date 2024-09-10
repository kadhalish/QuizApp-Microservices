package com.example.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.service.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	QuestionService service;

	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return service.getAllQuestions();
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCat(@PathVariable String category) {
		return service.getQuestionByCategory(category);
	}

	@PostMapping("/add")
	public String addQuestion(@RequestBody Question question) {
		return service.addQuestion(question);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteQuestion(@PathVariable int id) {
		return service.deleteQuestion(id);
	}

	@GetMapping("/generateQuiz")
	public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category, @RequestParam int number) {
		return service.generateQuiz(category, number);
	}

	@PostMapping("/getQuizQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> quesIds){
		return service.getQuestionsForQuiz(quesIds);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> valuateScore(@RequestBody List<Response> responses){
		return service.getScore(responses);
	}
}
