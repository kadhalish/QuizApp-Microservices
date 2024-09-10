package com.example.quizService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizService.feign.QuizInterface;
import com.example.quizService.model.QuestionWrapper;
import com.example.quizService.model.Quiz;
import com.example.quizService.model.Response;
import com.example.quizService.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepo;

	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questions = quizInterface.generateQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizRepo.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
		Quiz quiz = quizRepo.findById(id).get();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		List<Integer> question = quiz.getQuestionIds();
		questionsForUser = quizInterface.getQuestionsForQuiz(question).getBody();
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateScore(int id, List<Response> responses) {
		int right = 0;
		right = quizInterface.valuateScore(responses).getBody();
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
