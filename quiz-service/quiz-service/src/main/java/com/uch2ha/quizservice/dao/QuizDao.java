package com.uch2ha.quizservice.dao;

import com.uch2ha.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer>
{
}
