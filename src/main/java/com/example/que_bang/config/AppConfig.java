package com.example.que_bang.config;

import com.example.que_bang.domain.question.Essay;
import com.example.que_bang.question.form.QuestionForm;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.parser.Entity;

@Configuration
public class AppConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
            .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
            .setSourceNameTokenizer(NameTokenizers.UNDERSCORE);

    modelMapper.createTypeMap(QuestionForm.class, Essay.class)
            .addMapping(QuestionForm::getContent, Essay::setContent) //addMapping
    ;
    return modelMapper;
  }
}