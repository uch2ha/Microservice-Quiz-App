package com.uch2ha.questionservice.service;


import com.uch2ha.questionservice.dao.QuestionDao;
import com.uch2ha.questionservice.model.Question;
import com.uch2ha.questionservice.model.QuestionWrapper;
import com.uch2ha.questionservice.model.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService
{
  QuestionDao questionDao;

  public ResponseEntity<List<Question>> getAllQuestions()
  {
    try {
      return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
  }

  public ResponseEntity<List<Question>> getQuestionsByCategory(String category)
  {
    try {
      return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

  }

  public ResponseEntity<String> addQuestion(Question question)
  {
    questionDao.save(question);
    return new ResponseEntity<>("success", HttpStatus.CREATED);
  }

  public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer amount)
  {
    List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, amount);
    return new ResponseEntity<>(questions, HttpStatus.OK);
  }

  public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionIds)
  {
    List<QuestionWrapper> wrappers = new ArrayList<>();

    for (Integer id : questionIds) {
      Optional<Question> questionOptional = questionDao.findById(id);

      if (questionOptional.isPresent()) {
        Question question = questionOptional.get();
        QuestionWrapper wrapper = QuestionWrapper.builder()
                .id(question.getId())
                .questionTitle(question.getQuestionTitle())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build();

        wrappers.add(wrapper);
      }
    }

    return new ResponseEntity<>(wrappers, HttpStatus.OK);
  }

  public ResponseEntity<Integer> getScore(List<Response> responses)
  {
    int corrects = 0;

    for (Response response : responses) {
      Optional<Question> question = questionDao.findById(response.getId());
      if (question.isPresent()) {
        if (response.getResponse().equals(question.get().getRightAnswer())) {
          corrects++;
        }
      }
    }

    return new ResponseEntity<>(corrects, HttpStatus.OK);
  }
}
