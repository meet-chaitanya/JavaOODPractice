import com.stackoverflow.entity.Question;
import com.stackoverflow.entity.Tag;
import com.stackoverflow.entity.User;
import com.stackoverflow.repository.QuestionRepository;
import com.stackoverflow.service.QuestionService;

public class StackOverflow {
    public static void main(String[] args) {
        User userA = new User("123", "KC", "kc@gmail.com");
        User userB = new User("234", "Harsha", "sai@gmail.com");

        QuestionRepository questionRepository = new QuestionRepository();
        QuestionService questionService = new QuestionService(questionRepository);

        Question question = new Question("ABC", "Java programming", "Hello World", "123");

        questionService.saveQuestion(question);

        questionService.addTagToQuestion("ABC", new Tag("tag1", "ProgrammingLanguage"));

        questionService.voteOnQuestion("ABC", userA, true);

        questionService.voteOnQuestion("ABC", userB, true);
    }
}