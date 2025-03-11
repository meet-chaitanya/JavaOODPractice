import java.io.IOException;

public class Cut {
    public static void main(String[] args) {
        CommandLineArgsParser argsParser = new CommandLineArgsParser(args);
        argsParser.parse();

        InputSource inputSource = InputSourceFactory.createInputSource(argsParser.getFilePath());

        FieldsExtractionStrategy strategy = new DelimitedFieldsExtractionStrategy(argsParser.getDelimiter(), argsParser.getFieldNumbers());

        try {
            Processor processor = new Processor(inputSource, strategy);
            processor.process();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}