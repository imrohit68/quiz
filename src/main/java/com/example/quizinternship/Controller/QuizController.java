package com.example.quizinternship.Controller;

import com.example.quizinternship.DTO.*;
import com.example.quizinternship.Security.JwtTokenHelper;
import com.example.quizinternship.Service.QuizService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailService;
    private final JwtTokenHelper jwtTokenHelper;

    @PostMapping ("/quiz/token")
    private ResponseEntity<LoginResponse> authenticate(@RequestBody Login login){
        this.authenticate(login.getEmail(),login.getPassword());
        UserDetails userDetails = userDetailService.loadUserByUsername(login.getEmail());
        String token = jwtTokenHelper.generateToken(userDetails);
        return new ResponseEntity<>(new LoginResponse(userDetails.getUsername(), token), HttpStatus.OK);
    }

    @PostMapping("/quiz/start/{email}")
    public ResponseEntity<SuccessResponse> startQuiz
            (@PathVariable  String email) {
        return ResponseEntity.ok(new SuccessResponse("Quiz Session id is: " + quizService.createQuizSession(email)));
    }

    @GetMapping("/quiz/question")
    public ResponseEntity<QuestionDTO> getRandomQuestion() {
        return ResponseEntity.ok(quizService.getRandomQuestion());
    }

    @PostMapping("/quiz/answer")
    public ResponseEntity<SuccessResponse> submitAnswer(@RequestBody AnswerRequest answerRequest) {
        quizService.submitAnswer(answerRequest.getQuizSessionId(), answerRequest.getQuestionId(), answerRequest.getAnswerId());
        return ResponseEntity.ok(new SuccessResponse("Answer submitted successfully"));
    }

    @PostMapping("/quiz/complete/{quizSessionId}")
    public ResponseEntity<SuccessResponse> completeQuiz(@PathVariable Long quizSessionId) {
        quizService.completeQuizSession(quizSessionId);
        return ResponseEntity.ok(new SuccessResponse("Quiz session completed successfully"));
    }

    @GetMapping("/quiz/result/{quizSessionId}")
    public ResponseEntity<QuizSessionDTO> getQuizResult(@PathVariable Long quizSessionId) {
        return ResponseEntity.ok(quizService.getQuizSession(quizSessionId));
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}
