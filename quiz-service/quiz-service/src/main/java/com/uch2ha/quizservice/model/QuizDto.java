package com.uch2ha.quizservice.model;

import lombok.Data;

@Data
public class QuizDto
{
  String category;
  Integer amount;
  String title;
}