package com.example.quizinternship.Entity;

import com.example.quizinternship.DTO.QuizSessionDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class QuizSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startTime = LocalDateTime.now();

    private Integer totalQuestions = 0;

    private Integer correctAnswers = 0;

    @Enumerated(EnumType.STRING)
    private QuizStatus status = QuizStatus.ACTIVE;

    public QuizSessionDTO toDTO(){
        QuizSessionDTO quizSessionDTO = new QuizSessionDTO();
        quizSessionDTO.setId(id);
        quizSessionDTO.setStartTime(startTime);
        quizSessionDTO.setTotalQuestions(totalQuestions);
        quizSessionDTO.setCorrectAnswers(correctAnswers);
        return quizSessionDTO;
    }
    @Getter
    public enum QuizStatus {
        ACTIVE,
        COMPLETED
    }
}

