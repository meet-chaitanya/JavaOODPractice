public class HistoryCommand implements ShellCommand{
    private CommandHistory commandHistory;

    public HistoryCommand(CommandHistory history) {
        this.commandHistory = history;
    }
    @Override
    public void execute(String[] args) {
        commandHistory.getHistory().forEach(System.out::println);
    }
}
