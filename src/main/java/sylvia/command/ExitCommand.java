package sylvia.command;

import sylvia.configuration.Info;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public static final String MANUAL = "Usage: exit\n\n" + "Exits the application.\n\n" + "Aliases: ex, bye";

    /**
     * Constructs a new exit command.
     */
    public ExitCommand() {
        super("");
    }

    /**
     * Gets the manual for the exit command.
     *
     * @return The manual for the exit command.
     */
    public static String getManual() {
        return MANUAL;
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
