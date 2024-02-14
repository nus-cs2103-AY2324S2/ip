package chatbot.storage;

import java.util.Stack;

/**
 * This encapsulates the histories of a task list states.
 * <p>
 * We save the task list state at certain points in time,
 * and keep a log of forward actions so that
 * the state at any point in time can be restored.
 *
 * @author Titus Chew
 */
public final class SaveState {
    /** Stores the task list states. */
    private static final Stack<TaskListMemento> SAVED_STATES = new Stack<>();

    /**
     * Restores the previous state of the task list,
     * and if there is no previous state,
     * restores the initial state.
     * <p>
     * The initial state must be present before any rollback.
     *
     * @param rollbackBy The number of states to rollback by.
     * @return The previous state of the task list.
     */
    public static TaskListMemento rollback(int rollbackBy) {
        assert rollbackBy > 0;
        assert !SAVED_STATES.isEmpty();

        for (int i = 0; SAVED_STATES.size() > 1 && i < rollbackBy; i++) {
            SAVED_STATES.pop();
        }

        return SAVED_STATES.peek();
    }

    /**
     * Saves the current state of the task list.
     *
     * @param currentState The current state of the task list.
     */
    public static void saveCurrentState(TaskListMemento currentState) {
        SAVED_STATES.push(currentState);
    }
}
