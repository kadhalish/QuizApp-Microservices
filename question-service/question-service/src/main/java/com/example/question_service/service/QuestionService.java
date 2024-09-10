package com.example.question_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository repo;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public String addQuestion(Question question) {
		repo.save(question);
		return "Success";
	}

	public String deleteQuestion(int id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return "Success";
		}
		return "Fail";
	}

	// Generate Quiz Questions
	public ResponseEntity<List<Integer>> generateQuiz(String category, int number) {
		List<Integer> questionIds = repo.findRendomQuestionsByCategory(category, number);
		return new ResponseEntity<>(questionIds, HttpStatus.CREATED);
	}

	// Get Quiz
	public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> quesIds) {
		List<Question> questions = quesIds.stream().map(id -> repo.findById(id).get()).collect(Collectors.toList());
		List<QuestionWrapper> qw = new ArrayList<>();
		for (Question question : questions) {
			QuestionWrapper obj = new QuestionWrapper(question.getId(), question.getQuestiontitle(),
					question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			qw.add(obj);
		}
		return new ResponseEntity<>(qw, HttpStatus.OK);
	}

	// Calculate score
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right = 0;
		for (Response resp : responses) {
			String rightAnswer = repo.findById(resp.getId()).get().getCorrectanswer();
			if (resp.getAnswer().equals(rightAnswer)) {
				right++;
			}
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
