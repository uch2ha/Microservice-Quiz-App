package com.uch2ha.questionservice.controller;


import com.uch2ha.questionservice.model.Question;
import com.uch2ha.questionservice.model.QuestionWrapper;
import com.uch2ha.questionservice.model.Response;
import com.uch2ha.questionservice.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
@AllArgsConstructor
public class QuestionController
{

  QuestionService questionService;

  @GetMapping("")
  public ResponseEntity<List<Question>> getAllQuestions()
  {
    return questionService.getAllQuestions();
  }

  @PostMapping("")
  public ResponseEntity<String> addQuestion(@RequestBody Question question)
  {
    return questionService.addQuestion(question);
  }

  @GetMapping("category/{category}")
  public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
  {
    return questionService.getQuestionsByCategory(category);
  }

  @GetMapping("generate")
  public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,
                                                          @RequestParam Integer amount)
  {
    return questionService.getQuestionForQuiz(category, amount);
  }

  @PostMapping("getQuestions")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds)
  {
    return questionService.getQuestionsById(questionIds);
  }

  @PostMapping("getScore")
  public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
  {
    return questionService.getScore(responses);
  }

}
