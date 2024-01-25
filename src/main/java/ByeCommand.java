import java.util.List;

class ByeCommand extends Command<Void> {
    protected String command;
    protected List<String> arguments;

    public ByeCommand() {
        super("bye", List.of());
    }

    Void execute(List<Task> tasks) {
        return null;
    }
}
