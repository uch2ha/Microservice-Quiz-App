package com.uch2ha.quizservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Quiz-Service", version = "1.0.1"))
public class QuizServiceApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(QuizServiceApplication.class, args);
  }

}
