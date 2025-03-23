package com.stackoverflow.service;

import com.stackoverflow.entity.Answer;
import com.stackoverflow.entity.Comment;
import com.stackoverflow.entity.User;
import com.stackoverflow.repository.AnswerRepository;

import java.util.List;

public class AnswerService {
    private AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void saveAnswer(Answer answer) {
        answerRepository.addAnswer(answer);
    }

    public List<Answer> getAllAnswersForQuestion(String questionId) {
        return answerRepository.getAllAnswersToQuestion(questionId);
    }

    public synchronized void addCommentToAnswer(String answerId, Comment comment) {
        Answer answer = answerRepository.getAnswerById(answerId);
        answer.getComments().add(comment);
    }

    public synchronized void voteOnAnswer(String answerId, User user, boolean isUpvote) {
        Answer answer = answerRepository.getAnswerById(answerId);
        if (!answer.getAuthorId().equals(user.getUserId())) {
            if (isUpvote) {
                answer.upVote();
            } else {
                answer.downVote();
            }
            System.out.println("number of votes ( after " + user.getName() + " ) voted on answer " + answerId + " is: " + answer.getVoteCount());
        } else {
            System.out.println("User " + user.getName() + " can not vote on his own answer");
        }
    }
}
