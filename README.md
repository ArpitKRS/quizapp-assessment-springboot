# Quiz App
This is a Spring Boot application that allows users to take quizzes.

## Features
1. Start new quiz sessions.
2. Get random questions for the current session.
3. Submit answers to questions.
4. View results of completed sessions (total questions, correct   answers, incorrect answers).

## Installation
1. Clone this repository.
2. Ensure you have Java and Maven installed.
3. Run ```mvn clean install``` to build the project.

## Running the application
1. Open a terminal in the project directory.
2. Run ```mvn spring-boot:run``` to start the application.
3. The application will be available on port 8080 by default (http://localhost:8080/).

## API Endpoints
The API uses JSON for data exchange.

1. Start a new quiz session (POST):

* URL: http://localhost:8080/api/quiz/start
* Response: JSON body
```
{
  "id": <session_id>,
  "totalQuestions": 0,
  "correctAnswers": 0,
  "incorrectAnswers": 0
}
```

2. Get a random question for the current session (GET):

* URL: http://localhost:8080/api/quiz/question
* Headers:
    * Content-Type: application/json
* Response: JSON body
```
{
  "id": <question_id>,
  "text": "<question_text>",
  "optionA": "<option_a_text>",
  "optionB": "<option_b_text>",
  "optionC": "<option_c_text>",
  "optionD": "<option_d_text>",
  "correctAnswer": "<correct_answer_option>" (e.g., "A", "B")
}
```

3. Submit an answer to a question (POST):

* URL: http://localhost:8080/api/quiz/answer
* Headers:
    * Content-Type: application/json
Request: JSON body
```
{
  "sessionId": <session_id>,
  "questionId": <question_id>,
  "answer": "<user_answer_option>" (e.g., "A", "B")
}
```
* Response: (empty on success)

4. Get the results of a completed session (GET):

* URL: http://localhost:8080/api/quiz/result/<session_id>
* Response: JSON body
```
{
  "id": <session_id>,
  "totalQuestions": <total_questions_attempted>,
  "correctAnswers": <number_of_correct_answers>,
  "incorrectAnswers": <number_of_incorrect_answers>
}
```

## Using Postman

1. Open Postman and create a new collection named "Quiz App".
2. Create requests for each API endpoint.
3. Set the appropriate request method (GET, POST) and URLs.
4. For the "Start a new quiz session" and "Submit an answer"
requests, set the Content-Type header to "application/json".
5. In the "Body" tab for these requests, create a JSON object with the required parameters (e.g., ```sessionId```, ```questionId```, ```answer```).
6. Send the requests and view the responses.

Note: Replace ```<session_id>``` and ```<question_id>``` with the actual values obtained from previous API calls.
