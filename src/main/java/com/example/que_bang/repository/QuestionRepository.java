package com.example.que_bang.repository;


import com.example.que_bang.domain.question.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository<T extends Question> extends CrudRepository<T, Long> {
}
