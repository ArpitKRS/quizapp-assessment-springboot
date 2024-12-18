package com.example.quizapp.runner;

import com.example.quizapp.model.Question;
import com.example.quizapp.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    public DataInitializer(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Initializing database with questions...");

        if (questionRepository.count() == 0) {
            questionRepository
                    .save(new Question("What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "C"));
            questionRepository.save(new Question("What is 2 + 2?", "3", "4", "5", "6", "B"));
        }
    }
}