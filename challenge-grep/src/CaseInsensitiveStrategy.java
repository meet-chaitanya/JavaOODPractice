import java.util.regex.Pattern;

public class CaseInsensitiveStrategy implements PatternMatchingStrategy{
    @Override
    public Pattern compilePattern(String pattern) {
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }
}
