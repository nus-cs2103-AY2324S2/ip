package sylvia.command;

import sylvia.SylviaException;
import sylvia.state.ProgramState;
import sylvia.task.TaskList;

/**
 * Represents a command that can be undone and redone.
 */
public interface Undoable {
    /**
     * Undoes the command.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws SylviaException If the command fails to undo.
     */
    String undo(TaskList list, ProgramState state) throws SylviaException;

    /**
     * Redoes the command.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws SylviaException If the command fails to redo.
     */
    String redo(TaskList list, ProgramState state) throws SylviaException;
}
