package sylvia.command;

import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    public static final String MANUAL = "Usage: list\n\n" + "Lists all tasks in the task list.\n\n" + "Aliases: ls";

    /**
     * Constructs a new list command.
     */
    public ListCommand() {
        super("");
    }

    /**
     * Gets the manual for the list command.
     *
     * @return The manual for the list command.
     */
    public static String getManual() {
        return MANUAL;
    }

    /**
     * Executes the command. This command sets the program state to normal.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     */
    public String execute(TaskList list, ProgramState state) {
        state.setNormal();
        if (list.size() == 0) {
            return "You have no tasks in the list.";
        }
        return list.toString();
    }
}
