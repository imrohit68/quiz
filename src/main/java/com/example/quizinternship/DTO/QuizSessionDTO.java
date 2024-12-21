package com.example.quizinternship.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class QuizSessionDTO {
    private Long id;

    private LocalDateTime startTime = LocalDateTime.now();

    private Integer totalQuestions = 0;

    private Integer correctAnswers = 0;

}
