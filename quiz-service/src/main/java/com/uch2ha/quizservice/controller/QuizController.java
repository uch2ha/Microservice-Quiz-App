package com.uch2ha.quizservice.controller;

import com.uch2ha.quizservice.model.QuestionWrapper;
import com.uch2ha.quizservice.model.QuizDto;
import com.uch2ha.quizservice.model.Response;
import com.uch2ha.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {

  @Autowired
  QuizService quizService;

  @PostMapping("create")
  public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
    return quizService.createQuiz(quizDto.getCategory(), quizDto.getAmount(), quizDto.getTitle());
  }
  @GetMapping("get/{id}")
  public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
    return quizService.getQuizQuestions(id);
  }

  @PostMapping("submit/{id}")
  public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
    return quizService.calculateResult(id, responses);
  }


}
