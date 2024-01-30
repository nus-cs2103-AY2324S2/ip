package task;

public abstract class Command {
    private String body;

    protected Command(String body) {
        this.body = body;
    }

    protected String getBody() {
        return body;
    }

    public static Command parse(String input, CommandParser parser) throws UnknownCommandException {
        return parser.parse(input);
    }

    abstract public boolean execute(TaskList list) throws DukeException;
}
