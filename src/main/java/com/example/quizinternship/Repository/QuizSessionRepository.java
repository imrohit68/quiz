package com.example.quizinternship.Repository;

import com.example.quizinternship.Entity.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSessionRepository extends JpaRepository<QuizSession,Long> {
}
