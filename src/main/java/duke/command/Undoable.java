package duke.command;

import duke.DukeException;
import duke.state.ProgramState;
import duke.task.TaskList;

public interface Undoable {
    /**
     * Undoes the command.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the command fails to undo.
     */
    String undo(TaskList list, ProgramState state) throws DukeException;

    /**
     * Redoes the command.
     *
     * @param list  The task list to be modified.
     * @param state The program state to be modified.
     * @return The response to be displayed to the user.
     * @throws DukeException If the command fails to redo.
     */
    String redo(TaskList list, ProgramState state) throws DukeException;
}
