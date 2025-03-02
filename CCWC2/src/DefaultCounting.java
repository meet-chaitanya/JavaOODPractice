import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DefaultCounting implements CountBehavior {
    @Override
    public Result performCount(InputStream is) {
        Result result = new Result();
        int byteCount = 0;
        int wordCount = 0;
        int lineCount = 0;
        int charCount = 0;
        boolean inWord = false;
        boolean isNewLine = true;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))){
            int c;
            while((c = br.read()) != -1) {
                charCount++;
                byteCount += Character.toString((char)c).getBytes(StandardCharsets.UTF_8).length;
                if (Character.isWhitespace((char)c)) {
                    if (inWord) inWord = false;
                    if (c == '\n') {
                        isNewLine = true;
                        lineCount++;
                    }
                } else {
                    if (!inWord) {
                        inWord = true;
                        wordCount++;
                    }
                    isNewLine = false;
                }
            }
            if (!isNewLine) {
                lineCount++;
            }
        } catch (Exception e) {
            lineCount = -1;
            byteCount = -1;
            wordCount = -1;
            charCount = -1;
            System.out.println(e.getMessage());
        }
        if (lineCount > 0) {
            result.setLineCount(lineCount);
        }
        if (wordCount > 0) {
            result.setWordCount(wordCount);
        }
        if (byteCount > 0) {
            result.setByteCount(byteCount);
        }
        if (charCount > 0) {
            result.setCharacterCount(charCount);
        }
        return result;
    }
}
