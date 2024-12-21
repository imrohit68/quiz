package com.example.quizinternship.Repository;

import com.example.quizinternship.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query(value = """
    SELECT * FROM question
    ORDER BY RAND() 
    LIMIT 1
    """, nativeQuery = true)
    Question findRandomQuestion();
}
