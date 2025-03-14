import java.util.regex.Pattern;

public interface PatternMatchingStrategy {
    Pattern compilePattern(String pattern);
}
