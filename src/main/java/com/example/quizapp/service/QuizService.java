package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSession;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuizService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizSessionRepository quizSessionRepository;

    public QuizSession startQuizSession() {
        QuizSession session = new QuizSession();
        session.setTotalQuestions(0);
        session.setCorrectAnswers(0);
        session.setIncorrectAnswers(0);
        return quizSessionRepository.save(session);
    }

    public Question getRandomQuestion() {
        long count = questionRepository.count();
        if (count == 0) {
            throw new RuntimeException("No questions available in the database");
        }

        // Fetch all questions and pick a random one
        List<Question> questions = questionRepository.findAll();
        int randomIndex = new Random().nextInt((int) count);
        return questions.get(randomIndex);
    }

    public void submitAnswer(Long sessionId, Long questionId, String answer) {
        var sessionOpt = quizSessionRepository.findById(sessionId);
        var questionOpt = questionRepository.findById(questionId);

        if (sessionOpt.isEmpty() || questionOpt.isEmpty()) {
            throw new RuntimeException("Session or question not found");
        }

        QuizSession session = sessionOpt.get();
        Question question = questionOpt.get();

        session.setTotalQuestions(session.getTotalQuestions() + 1);
        if (question.getCorrectAnswer().equalsIgnoreCase(answer)) {
            session.setCorrectAnswers(session.getCorrectAnswers() + 1);
        } else {
            session.setIncorrectAnswers(session.getIncorrectAnswers() + 1);
        }
        quizSessionRepository.save(session);
    }

    public QuizSession getQuizSession(Long sessionId) {
        return quizSessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }
}
