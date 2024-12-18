package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSession;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/start")
    public QuizSession startQuiz() {
        return quizService.startQuizSession();
    }

    @GetMapping("/question")
    public Question getRandomQuestion() {
        try {
            return quizService.getRandomQuestion();
        } catch (Exception e) {
            System.err.println("Error fetching question: " + e.getMessage());
            throw e;
        }
    }

    @PostMapping("/answer")
    public void submitAnswer(@RequestBody AnswerRequest request) {
        quizService.submitAnswer(request.getSessionId(), request.getQuestionId(), request.getAnswer());
    }

    @GetMapping("/result/{sessionId}")
    public QuizSession getQuizResults(@PathVariable Long sessionId) {
        return quizService.getQuizSession(sessionId);
    }
}

class AnswerRequest {
    private Long sessionId;
    private Long questionId;
    private String answer;

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}