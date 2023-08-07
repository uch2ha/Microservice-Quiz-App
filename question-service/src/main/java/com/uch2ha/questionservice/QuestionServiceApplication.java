package com.uch2ha.questionservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Question-Service", version = "1.0.1"))
public class QuestionServiceApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(QuestionServiceApplication.class, args);
  }

}
