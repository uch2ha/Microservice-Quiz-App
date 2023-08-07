package com.uch2ha.quizservice.feign;


import com.uch2ha.quizservice.model.QuestionWrapper;
import com.uch2ha.quizservice.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface
{
  @GetMapping("api/v1/question/generate")
  public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,
                                                          @RequestParam Integer amount);

  @PostMapping("api/v1/question/getQuestions")
  public ResponseEntity<List<QuestionWrapper>> getQuestionsById(@RequestBody List<Integer> questionIds);

  @PostMapping("api/v1/question/getScore")
  public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);

}
