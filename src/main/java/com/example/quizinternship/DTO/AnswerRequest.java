package com.example.quizinternship.DTO;


import lombok.Data;

@Data
public class AnswerRequest {

    private Long quizSessionId;

    private Long questionId;

    private Long answerId;
}
