package com.stackoverflow.repository;

import com.stackoverflow.entity.Question;
import com.stackoverflow.entity.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class QuestionRepository {
    private Map<String, Question> questionMap;

    public QuestionRepository() {
        this.questionMap = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questionMap.put(question.getId(), question);
    }

    public Question getQuestionByID(String questionId) {
        return questionMap.get(questionId);
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questionMap.values());
    }

    public List<Question> getQuestionsByTag(Tag tag) {
        return questionMap.values()
                .stream()
                .filter(q -> q.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionsByKeyword(String keyword) {
        return questionMap.values()
                .stream()
                .filter(q -> q.getTitle().contains(keyword) || q.getContent().contains(keyword))
                .collect(Collectors.toList());
    }
}
