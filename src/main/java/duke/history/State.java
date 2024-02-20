package duke.history;

import java.util.ArrayList;

import duke.command.Command;
import duke.tasks.Task;

/**
 * The `State` class represents a snapshot of the HAL9000 application's state at a specific point in time.
 * It contains information about the executed command and the list of tasks at that time.
 */
public class State {
    private final Command command;
    private final ArrayList<Task> taskList;

    /**
     * Constructs a new State object with the given command and task list.
     *
     * @param cmd  The command executed to reach this state.
     * @param list The list of tasks at this state.
     */
    public State(Command cmd, ArrayList<Task> list) {
        command = cmd;
        taskList = list;
    }

    /**
     * Gets the list of tasks at this state.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the command executed to reach this state.
     *
     * @return The command.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Checks if this state should be ignored in the command history.
     *
     * @return true if the state should be ignored, false otherwise.
     */
    public boolean isIgnoredHistory() {
        return command.isIgnoredHistory();
    }
}
