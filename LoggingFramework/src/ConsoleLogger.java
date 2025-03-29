public class ConsoleLogger extends AbstractLogger{
    @Override
    public void writeLog(String msg) {
        System.out.println("CONSOLE: " + msg);
    }
}
