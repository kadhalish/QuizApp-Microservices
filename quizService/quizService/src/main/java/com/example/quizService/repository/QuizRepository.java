package com.example.quizService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizService.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
