package duke.command;

import duke.configuration.Info;
import duke.state.ProgramState;
import duke.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("");
    }

    /**
     * Executes the command. This command sets the program state to exit.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     */
    public String execute(TaskList list, ProgramState state) {
        String response = Info.EXIT_MESSAGE;
        state.setExit();
        return response;
    }
}
