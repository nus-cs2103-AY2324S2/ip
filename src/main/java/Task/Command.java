package task;

import duke.ProgramState;

public abstract class Command {
    private String body;

    protected Command(String body) {
        this.body = body;
    }

    protected String getBody() {
        return body;
    }

    public static Command parse(String input, CommandParser parser) throws DukeException {
        return parser.parse(input);
    }

    public abstract String execute(TaskList list, ProgramState state) throws DukeException;
}
