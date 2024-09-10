package com.example.quizService.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizService.model.QuestionWrapper;
import com.example.quizService.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("/questions/generateQuiz")
	public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category, @RequestParam int number);
	

	@PostMapping("/questions/getQuizQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> quesIds);
	
	
	@PostMapping("/questions/getScore")
	public ResponseEntity<Integer> valuateScore(@RequestBody List<Response> responses);
	
}
