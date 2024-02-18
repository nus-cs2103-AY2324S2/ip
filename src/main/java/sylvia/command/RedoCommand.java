package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command to redo the most recent command that was undone.
 */
public class RedoCommand extends Command {
    public static final String MANUAL = "Usage: redo\n" + "Redoes the most recent command that was undone.\n"
            + "Aliases: rd";

    /**
     * Gets the manual for the redo command.
     *
     * @return The manual for the redo command.
     */
    public static String getManual() {
        return MANUAL;
    }

    /**
     * Creates a new redo command.
     *
     * @param body The body of the command.
     */
    public RedoCommand(String body) {
        super(body);
    }

    /**
     * Executes the command. This command redoes the most recent command that was
     * undone. The program state is set to normal after the command is executed,
     * even if the command fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws SylviaException If the command fails to execute.
     */
    @Override
    public String execute(TaskList list, ProgramState state) throws SylviaException {
        String response;
        if (!state.isRedoable()) {
            response = "Nothing to redo now.";
            return response;
        }
        Undoable command = state.getHistory().getLastUndoneCommand();
        response = command.redo(list, state);
        state.setNormal();
        return response;
    }
}
