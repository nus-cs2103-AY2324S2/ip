package chatbot.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This encapsulates the histories of a task list states
 * and modifying command inputs that are successful.
 * <p>
 * We save the task list state at certain points in time,
 * and keep a log of forward actions so that
 * the state at any point in time can be restored.
 *
 * @author Titus Chew
 */
public final class SaveState {
    /** Stores the task list states. */
    private static final Stack<TaskListMemento> SAVED_TASK_LISTS = new Stack<>();

    /** Stores the saved command input lines */
    private static final Stack<String> SAVED_COMMAND_LINES = new Stack<>();

    /**
     * Restores the previous state of the task list,
     * and if there is no previous state,
     * restores the initial state.
     * <p>
     * The initial state must be present before any rollback.
     *
     * @param rollbackBy The number of states to rollback by.
     * @return The command line input that are rolled back.
     */
    public static String[] rollback(int rollbackBy) {
        assert rollbackBy > 0;
        assert !SAVED_TASK_LISTS.isEmpty();

        List<String> rolledBackCommands = new ArrayList<>();

        // perform the rollback
        for (int i = 0; canRollbackOnce() && i < rollbackBy; i++) {
            SAVED_TASK_LISTS.pop();
            rolledBackCommands.add(SAVED_COMMAND_LINES.pop());
        }

        return rolledBackCommands
                .stream()
                .toArray(String[]::new);
    }

    /**
     * Checks if the saved state can be rolled back.
     *
     * @return True if the saved state can be rolled back, otherwise false.
     */
    private static boolean canRollbackOnce() {
        return SAVED_TASK_LISTS.size() > 1 && SAVED_COMMAND_LINES.size() > 0;
    }

    /**
     * Queries the current state of the task list in the history.
     */
    public static TaskListMemento queryCurrentState() {
        assert !SAVED_TASK_LISTS.isEmpty();
        return SAVED_TASK_LISTS.peek();
    }

    /**
     * Saves the current state of the task list in the history.
     *
     * @param currentState The current state of the task list.
     */
    public static void saveCurrentState(TaskListMemento currentState) {
        SAVED_TASK_LISTS.push(currentState);
    }

    /**
     * Saves the current state of the task list.
     *
     * @param currentState The current state of the task list.
     */
    public static void saveCurrentState(TaskListMemento currentState, String commandLineInput) {
        saveCurrentState(currentState);
        SAVED_COMMAND_LINES.push(commandLineInput.trim());
    }
}
