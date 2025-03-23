Core Features:
- User can post a Question
- User can Answer a Question
- User can comment on Question / Answer
- User can vote on Question / Answer
- User can add a tag to Question
- User can search Questions by keywords, tags etc.
- Author of Question can accept an Answer
- Handle concurrency for comments and votes
- Handle scale of large number of questions and answers

Core Entities:
- User: Id, Name, Email
- Question: Id, Title, Content, AuthorId, upVotes, downVotes, Comments[]
- Answer: Id, QuestionId, Content, isAccepted, upVotes, downVotes, Comments[]
- Comment: Id, Content
- Tag: id, String

Interfaces:
- Votable: upVote(), downVote(), getVoteCount()
- Question implements Votable
- Answer implements Votable

Repository:
QuestionRepository:
- Map<String, Question> questionsMap
- saveQuestion
- voteOnQuestion
- commentOnQuestion
- getAllQuestions

AnswerRepository:
- Map<String, Answer> answerMap
- saveAnswer
- voteOnAnswer
- commentOnAnswer
- getAllAnswerToQuestion

