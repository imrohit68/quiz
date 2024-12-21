package com.example.quizinternship.Service;

import com.example.quizinternship.DTO.UserDTO;
import com.example.quizinternship.Entity.Answer;
import com.example.quizinternship.Entity.Question;
import com.example.quizinternship.Repository.AnswerRepository;
import com.example.quizinternship.Repository.QuestionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataSeeder {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final QuizService quizService;

    @PostConstruct
    public void seedData() {
        addQuestionWithAnswers(
                "What is the capital of France?",
                List.of("Paris", "London", "Berlin", "Madrid"),
                0
        );

        addQuestionWithAnswers(
                "Which planet is known as the Red Planet?",
                List.of("Mars", "Venus", "Jupiter", "Mercury"),
                0
        );

        addQuestionWithAnswers(
                "What is the chemical symbol for gold?",
                List.of("Au", "Ag", "Fe", "Cu"),
                0
        );

        addQuestionWithAnswers(
                "Who painted the Mona Lisa?",
                List.of("Leonardo da Vinci", "Michelangelo", "Pablo Picasso", "Vincent van Gogh"),
                0
        );

        addQuestionWithAnswers(
                "What is the largest ocean on Earth?",
                List.of("Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"),
                0
        );

        addQuestionWithAnswers(
                "What is the currency of Japan?",
                List.of("Yen", "Won", "Yuan", "Ringgit"),
                0
        );

        addQuestionWithAnswers(
                "What is the square root of 144?",
                List.of("12", "14", "10", "16"),
                0
        );

        addQuestionWithAnswers(
                "What is the chemical formula for water?",
                List.of("H2O", "CO2", "O2", "NH3"),
                0
        );

        addQuestionWithAnswers(
                "Which is the smallest prime number?",
                List.of("2", "1", "3", "0"),
                0
        );

        addQuestionWithAnswers(
                "What is the capital of Australia?",
                List.of("Canberra", "Sydney", "Melbourne", "Perth"),
                0
        );

        addQuestionWithAnswers(
                "Who invented the telephone?",
                List.of("Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Albert Einstein"),
                0
        );

        addQuestionWithAnswers(
                "Which country is known as the Land of Rising Sun?",
                List.of("Japan", "China", "South Korea", "Thailand"),
                0
        );

        addQuestionWithAnswers(
                "What is 20% of 200?",
                List.of("40", "20", "50", "30"),
                0
        );

        addQuestionWithAnswers(
                "Which gas do plants absorb from the atmosphere?",
                List.of("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"),
                0
        );

        addQuestionWithAnswers(
                "Who wrote 'Romeo and Juliet'?",
                List.of("William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain"),
                0
        );

        addQuestionWithAnswers(
                "What is the largest planet in our solar system?",
                List.of("Jupiter", "Saturn", "Neptune", "Uranus"),
                0
        );

        addQuestionWithAnswers(
                "What is the capital of India?",
                List.of("New Delhi", "Mumbai", "Bangalore", "Kolkata"),
                0
        );

        addQuestionWithAnswers(
                "Which is the hardest natural substance?",
                List.of("Diamond", "Gold", "Iron", "Platinum"),
                0
        );

        addQuestionWithAnswers(
                "What is the main component of the Sun?",
                List.of("Hydrogen", "Helium", "Oxygen", "Nitrogen"),
                0
        );

        addQuestionWithAnswers(
                "How many continents are there on Earth?",
                List.of("7", "5", "6", "8"),
                0
        );

        quizService.createUser(new UserDTO("Rohit","sirohit328@gmail.com","123456"));
    }

    private void addQuestionWithAnswers(String questionText, List<String> answers, int correctIndex) {
        Question question = new Question();
        question.setQuestionText(questionText);
        question = questionRepository.save(question);

        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setAnswerText(answers.get(i));
            answerList.add(answer);
        }
        answerList = answerRepository.saveAll(answerList);

        question.setCorrectAnswerId(answerList.get(correctIndex).getId());
        questionRepository.save(question);
    }
}