package com.example.que_bang.modules.question;

import org.springframework.data.repository.CrudRepository;

public interface EssayRepository extends QuestionRepository<Essay>, CrudRepository<Essay, Long> {
}
