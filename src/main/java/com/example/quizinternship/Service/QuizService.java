package com.example.quizinternship.Service;

import com.example.quizinternship.DTO.QuestionDTO;
import com.example.quizinternship.DTO.QuizSessionDTO;
import com.example.quizinternship.DTO.UserDTO;
import com.example.quizinternship.Entity.Question;
import com.example.quizinternship.Entity.QuizSession;
import com.example.quizinternship.Entity.User;
import com.example.quizinternship.Exceptions.InvalidRequestException;
import com.example.quizinternship.Repository.AnswerRepository;
import com.example.quizinternship.Repository.QuestionRepository;
import com.example.quizinternship.Repository.QuizSessionRepository;
import com.example.quizinternship.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuestionRepository questionRepository;
    private final QuizSessionRepository quizSessionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long createQuizSession(String userEmail) {
        User user = userRepository.findById(userEmail)
                .orElseThrow(() -> new InvalidRequestException("User not found with email " + userEmail));
        QuizSession quizSession = new QuizSession();
        quizSession.setUser(user);
        QuizSession savedQuizSession = quizSessionRepository.save(quizSession);
        return savedQuizSession.getId();
    }
    public QuestionDTO getRandomQuestion() {
        return questionRepository.findRandomQuestion().toDTO();
    }
    public void submitAnswer(Long quizSessionId, Long questionId, Long answerId) {
        QuizSession quizSession = quizSessionRepository.findById(quizSessionId)
                .orElseThrow(() -> new InvalidRequestException("Quiz session not found with id " + quizSessionId));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new InvalidRequestException("Question not found with id " + questionId));
        quizSession.setTotalQuestions(quizSession.getTotalQuestions() + 1);
        if (question.getCorrectAnswerId().equals(answerId)) {
            quizSession.setCorrectAnswers(quizSession.getCorrectAnswers() + 1);
        }
        quizSessionRepository.save(quizSession);
    }
    public void completeQuizSession(Long quizSessionId) {
        QuizSession quizSession = quizSessionRepository.findById(quizSessionId)
                .orElseThrow(() -> new InvalidRequestException("Quiz session not found with id " + quizSessionId));
        quizSession.setStatus(QuizSession.QuizStatus.COMPLETED);
        quizSessionRepository.save(quizSession);
    }
    public QuizSessionDTO getQuizSession(Long quizSessionId) {
        QuizSession quizSession = quizSessionRepository.findById(quizSessionId)
                .orElseThrow(() -> new InvalidRequestException("Quiz session not found with id " + quizSessionId));
        if(quizSession.getStatus().equals(QuizSession.QuizStatus.ACTIVE)){
            throw new InvalidRequestException("Quiz session is still active, Please complete the quiz session");
        }
        return quizSession.toDTO();
    }
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return savedUser.toUserDTO();
    }
    public UserDTO getUserById(String email) {
        return userRepository.findById(email)
                .orElseThrow(()-> new InvalidRequestException("User does not exist with id: "+email)).toUserDTO();
    }
}
