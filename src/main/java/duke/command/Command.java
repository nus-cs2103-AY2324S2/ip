package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.TaskList;

/**
 * Represents a command that can be executed by the chatbot.
 */
public abstract class Command {
    private String body;

    protected Command(String body) {
        this.body = body;
    }

    protected String getBody() {
        return body;
    }

    /**
     * Parses the input and returns a command.
     *
     * @param input  The input to parse.
     * @param parser The parser to use.
     * @return The parsed command.
     * @throws DukeException If the input is invalid.
     */
    public static Command parse(String input, CommandParser parser) throws DukeException {
        return parser.parse(input);
    }

    /**
     * Executes the command.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the command fails to execute.
     */
    public abstract String execute(TaskList list, ProgramState state) throws DukeException;
}
