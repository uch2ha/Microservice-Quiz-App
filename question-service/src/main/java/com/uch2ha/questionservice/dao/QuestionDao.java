package com.uch2ha.questionservice.dao;

import com.uch2ha.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>
{
  List<Question> findByCategory(String category);

  @Query(value = "SELECT q.id FROM question q " +
          "WHERE q.category =:category " +
          "ORDER BY RANDOM() " +
          "LIMIT :amount", nativeQuery = true)
  List<Integer> findRandomQuestionsByCategory(String category, Integer amount);
}
