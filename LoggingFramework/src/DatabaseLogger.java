public class DatabaseLogger extends AbstractLogger{
    @Override
    public void writeLog(String msg) {
        System.out.println("Database: " + msg);
    }
}
