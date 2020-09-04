package com.example.que_bang.repository;

import com.example.que_bang.domain.QuestionBundle;
import com.example.que_bang.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBundleRepository extends JpaRepository<QuestionBundle, Long> {
}
