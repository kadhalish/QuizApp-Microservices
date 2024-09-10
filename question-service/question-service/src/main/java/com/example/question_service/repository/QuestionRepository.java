package com.example.question_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.question_service.model.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	List<Question> findByCategory(String category);

	@Query(value = "Select * from (Select q.id from question q where q.category=:category ORDER BY DBMS_RANDOM.VALUE) where rownum<=:numQ", nativeQuery = true)
	List<Integer> findRendomQuestionsByCategory(String category, int numQ);

}
