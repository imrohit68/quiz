package com.example.quizinternship.Entity;

import com.example.quizinternship.DTO.AnswerDTO;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answerText;

    public AnswerDTO toDTO() {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(id);
        answerDTO.setAnswerText(answerText);
        return answerDTO;
    }

}
