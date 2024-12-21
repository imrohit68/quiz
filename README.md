# Quiz App with JWT Authentication

## Overview
This is a Spring Boot based application that allows users to take a quiz. The app uses JWT for authentication, and provides functionalities like starting a new quiz session, getting a random question, submitting answers, and getting the quiz session results.

## Flow of the Application

1. **Login & Authentication**
   - A user logs in with their email and password.
   - A JWT token is generated upon successful authentication.
   
2. **Create Quiz Session**
   - Once the user is authenticated, a quiz session is created using the `email` and the generated JWT token.

3. **Getting a Random Question**
   - The user can fetch a random multiple-choice question from the database.

4. **Submit an Answer**
   - The user can submit an answer for the fetched question.

5. **Complete the Quiz Session**
   - The user can complete the quiz session, after which the result (correct and incorrect answers) is available.

6. **Get Quiz Session Result**
   - The user can get the details of their completed quiz session, including the total questions answered, and statistics about correct and incorrect answers.

---

## APIs

### 1. **Login and Get JWT Token**
   - **Endpoint:** `POST /quiz/token`
   - **Request Body:**
     ```json
     {
       "email": "sirohit328@gmail.com",
       "password": "123456"
     }
     ```
   - **Response:**
     ```json
     {
       "username": "sirohit328@gmail.com",
       "token": "your_jwt_token"
     }
     ```

### 2. **Start a New Quiz Session**
   - **Endpoint:** `POST /quiz/start/{email}`
   - **Parameters:**
     - `email` (path parameter)
   - **Response:**
     ```json
     {
       "message": "Quiz Session id is: <quiz_session_id>"
     }
     ```

### 3. **Get a Random Question**
   - **Endpoint:** `GET /quiz/question`
   - **Headers:** 
     - `Authorization: Bearer <jwt_token>`
   - **Response:**
     ```json
     {
       "questionId": 1,
       "question": "What is the capital of France?",
       "options": [
         { "optionId": 1, "option": "Paris" },
         { "optionId": 2, "option": "Berlin" },
         { "optionId": 3, "option": "Madrid" },
         { "optionId": 4, "option": "Rome" }
       ]
     }
     ```

### 4. **Submit an Answer**
   - **Endpoint:** `POST /quiz/answer`
   - **Request Body:**
     ```json
     {
       "quizSessionId": 123,
       "questionId": 1,
       "answerId": 1
     }
     ```
   - **Response:**
     ```json
     {
       "message": "Answer submitted successfully"
     }
     ```

### 5. **Complete Quiz Session**
   - **Endpoint:** `POST /quiz/complete/{quizSessionId}`
   - **Parameters:**
     - `quizSessionId` (path parameter)
   - **Response:**
     ```json
     {
       "message": "Quiz session completed successfully"
     }
     ```

### 6. **Get Quiz Session Result**
   - **Endpoint:** `GET /quiz/result/{quizSessionId}`
   - **Parameters:**
     - `quizSessionId` (path parameter)
   - **Response:**
     ```json
     {
       "quizSessionId": 123,
       "totalQuestions": 5,
       "correctAnswers": 3,
       "incorrectAnswers": 2,
       "questions": [
         {
           "questionId": 1,
           "question": "What is the capital of France?",
           "submittedAnswer": "Paris",
           "correctAnswer": "Paris",
           "isCorrect": true
         },
         ...
       ]
     }
     ```

---

## Base URL
The base URL for the API is: http://13.203.76.146
## POSTMAN COLLECTION LINK : https://cloudy-station-308994.postman.co/workspace/Team-Workspace~9ce5e1ec-0a2d-47aa-850d-c6fdc089537e/collection/26470704-d021caca-754a-4d1f-81fa-1d1a73963879?action=share&creator=26470704
