package com.example.que_bang.repository;

import com.example.que_bang.domain.question.Essay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EssayRepository extends QuestionRepository<Essay>, CrudRepository<Essay, Long> {
}
