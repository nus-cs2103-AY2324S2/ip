package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command to undo the most recent command that modified the task
 * list.
 */
public class UndoCommand extends Command {
    public static final String MANUAL = "Usage: undo\n"
            + "Undoes the most recent command that modified the task list.\n" + "Aliases: ud";

    /**
     * Creates a new undo command.
     *
     * @param body The body of the command.
     */
    public UndoCommand(String body) {
        super(body);
    }

    /**
     * Executes the command. This command undoes the most recent command that
     * modified the task list. The program state is set to normal after the command
     * is executed, even if the command fails to execute.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws SylviaException If the command fails to execute.
     */
    @Override
    public String execute(TaskList list, ProgramState state) throws SylviaException {
        String response;
        if (!state.isUndoable()) {
            response = "Nothing to undo now.";
            return response;
        }
        Undoable command = state.getHistory().getLatestCommand();
        response = command.undo(list, state);
        state.setNormal();
        return response;
    }
}
