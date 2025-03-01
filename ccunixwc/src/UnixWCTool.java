import java.io.*;

public class UnixWCTool {
    public static void main(String[] args) {

        String option = args.length >= 1 ? args[0] : "";
        String fileName = args[args.length - 1];

        WCHelper wcHelper = new WCHelper(new File(fileName));
        Result result = new Result();
        switch (option) {
            case "-l" -> {
                result.setLineCount(wcHelper.getLineCount());
            }
            case "-w" -> {
               result.setWordCount(wcHelper.getWordCount());
            }
            case "-c" -> {
                result.setCharacterCount(wcHelper.getCharacterCount());
            }
            case "-m" -> {
                result.setByteCount(wcHelper.getByteCount());
            }
            default -> {
                result.setLineCount(wcHelper.getLineCount());
                result.setWordCount(wcHelper.getWordCount());
                result.setCharacterCount(wcHelper.getCharacterCount());
            }
        }

        System.out.println(result + " " + fileName);

    }
}