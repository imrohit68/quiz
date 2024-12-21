package com.example.quizinternship.Repository;

import com.example.quizinternship.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

}
