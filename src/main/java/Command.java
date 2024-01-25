import java.util.List;

abstract class Command<T> {
    protected String command;
    protected List<String> arguments;

    Command(String command, List<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    abstract T execute(List<Task> tasks);

    public String getCommand() {
        return this.command;
    }
}
