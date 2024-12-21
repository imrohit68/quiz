package com.example.quizinternship.Entity;

import com.example.quizinternship.DTO.QuestionDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answers;

    private Long correctAnswerId;

    public QuestionDTO toDTO() {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(id);
        questionDTO.setQuestionText(questionText);
        questionDTO.setAnswers(answers.stream().map(Answer::toDTO).toList());
        return questionDTO;
    }
}
