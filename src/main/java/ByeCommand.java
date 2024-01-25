import java.util.List;

class ByeCommand extends Command<List<Task>> {
    protected String command;
    protected List<String> arguments;

    public ByeCommand() {
        super("bye", List.of());
    }

    List<Task> execute(List<Task> tasks) {
        return tasks;
    }
}
