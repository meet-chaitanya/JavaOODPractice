import java.io.File;

public class CdCommand implements ShellCommand{
    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Usage: cd directory is missing!");
        }
        String dir = args[0];
        File directory = new File(dir);
        if (directory.exists() && directory.isDirectory()) {
            System.setProperty("user.dir", dir);
        } else {
            System.err.println("file or directory does not exist: " + dir);
        }
    }
}
