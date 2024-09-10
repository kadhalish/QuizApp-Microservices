package com.example.quizService.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "use_quiz_seq")
	@SequenceGenerator(name="use_quiz_seq", sequenceName = "quiz_id_seq", allocationSize = 1)
	private int id;
	private String title;
	@ElementCollection
	private List<Integer> questionIds;

}
