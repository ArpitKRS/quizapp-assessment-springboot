package com.example.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuizSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
}
