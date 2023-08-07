package com.uch2ha.quizservice.service;

import com.uch2ha.quizservice.dao.QuizDao;
import com.uch2ha.quizservice.feign.QuizInterface;
import com.uch2ha.quizservice.model.QuestionWrapper;
import com.uch2ha.quizservice.model.Quiz;
import com.uch2ha.quizservice.model.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{
  @Autowired
  QuizDao quizDao;
  @Autowired
  QuizInterface quizInterface;

  public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

    List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();

    System.out.println(questions);

    Quiz quiz = Quiz.builder()
            .title(title)
            .questionIds(questions)
            .build();

    quizDao.save(quiz);

    return new ResponseEntity<>("Success", HttpStatus.CREATED);

  }

  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
    Optional<Quiz> quiz = quizDao.findById(id);

    if(quiz.isEmpty()){
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    List<Integer> questionsIds = quiz.get().getQuestionIds();

    return quizInterface.getQuestionsById(questionsIds);

  }

  public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

    return quizInterface.getScore(responses);
  }
}
