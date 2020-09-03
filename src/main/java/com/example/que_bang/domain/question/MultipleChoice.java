package com.example.que_bang.domain.question;

import com.example.que_bang.domain.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter
@Setter
public class MultipleChoice extends Question {
}
