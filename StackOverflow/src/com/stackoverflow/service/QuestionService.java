package com.stackoverflow.service;

import com.stackoverflow.entity.Comment;
import com.stackoverflow.entity.Question;
import com.stackoverflow.entity.Tag;
import com.stackoverflow.entity.User;
import com.stackoverflow.repository.QuestionRepository;

public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveQuestion(Question question) {
        questionRepository.addQuestion(question);
    }

    public synchronized void addCommentToQuestion(String questionId, Comment comment) {
        Question question = questionRepository.getQuestionByID(questionId);
        question.getComments().add(comment);
    }

    public void addTagToQuestion(String questionId, Tag tag) {
        Question question = questionRepository.getQuestionByID(questionId);
        question.getTags().add(tag);
        System.out.println("added Tag: " + tag.getName() + " to Question: " + questionId);
    }

    public synchronized void voteOnQuestion(String questionId, User user, boolean isUpVote) {
        Question question = questionRepository.getQuestionByID(questionId);
        if (!question.getAuthorId().equals(user.getUserId())) {
            if (isUpVote) {
                question.upVote();
            } else {
                question.downVote();
            }
            System.out.println("number of votes ( after " + user.getName() + " ) voted " + questionId + " is: " + question.getVoteCount());
        } else {
            System.out.println("User " + user.getName() + " can not vote on his own question");
        }
    }

}
