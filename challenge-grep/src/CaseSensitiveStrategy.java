import java.util.regex.Pattern;

public class CaseSensitiveStrategy implements PatternMatchingStrategy{
    @Override
    public Pattern compilePattern(String pattern) {
        return Pattern.compile(pattern);
    }
}
