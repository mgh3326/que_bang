package com.example.que_bang.repository;


import com.example.que_bang.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
