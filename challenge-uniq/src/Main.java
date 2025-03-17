public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage java Main.java <inputFileName>");
            System.exit(1);
        }

        CommandLineArgsParser argsParser = new CommandLineArgsParser(args);
        String fileName = argsParser.getFilePath();
        boolean unique = argsParser.isUnique();
        boolean repeated = argsParser.isRepeated();
        boolean count = argsParser.isCount();

        InputSource inputSource = fileName.equals("-") ? new StdinInputSource() : new FileInputSource(fileName);

        Processor processor = new Processor(inputSource);
        UniqFactory factory = new UniqFactory();
        UniqStrategy strategy = factory.createUniqStrategy(unique, repeated, count);

        processor.setStrategy(strategy);
        processor.process();
    }
}