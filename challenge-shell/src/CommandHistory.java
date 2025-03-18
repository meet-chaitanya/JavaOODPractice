import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CommandHistory {
    private List<String> history = new ArrayList<>();
    private File historyFile;

    public CommandHistory() {
        String homeDir = System.getProperty("user.dir");
        this.historyFile = new File(homeDir, ".ccsh_history");
        loadHistory();
    }

    public void addCommand(String command) {
        history.add(command);
    }

    public List<String> getHistory() {
        return history;
    }

    public void saveHistory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile))){
            for (String cmd: history) {
                writer.write(cmd);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void loadHistory() {
        if (!historyFile.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
