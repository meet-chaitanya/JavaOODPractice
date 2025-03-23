package com.stackoverflow.repository;

import com.stackoverflow.entity.Answer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnswerRepository {
    private Map<String, Answer> answerMap;

    public AnswerRepository() {
        this.answerMap = new HashMap<>();
    }

    public void addAnswer(Answer answer) {
        answerMap.put(answer.getId(), answer);
    }

    public Answer getAnswerById(String answerId) {
        return answerMap.get(answerId);
    }

    public List<Answer> getAllAnswersToQuestion(String questionId) {
        return answerMap.values()
                .stream()
                .filter(a -> a.getQuestionId().equals(questionId))
                .collect(Collectors.toList());
    }
}
