package duke.history;

import duke.exceptions.ProcessingException;
import duke.storage.Storage;

/**
 * The `HistoryManager` class manages the history of commands in the Duke application.
 * It allows for undoing and redoing commands and updating the command history.
 */
public class HistoryManager {
    private final History history;

    /**
     * Constructs a new HistoryManager with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public HistoryManager(State startState) {
        history = new History(startState);
    }

    /**
     * Undoes the last command and restores the previous state.
     *
     * @param storage The storage system for managing tasks.
     * @return A message indicating the command was undone and the current list of tasks.
     * @throws ProcessingException If there is an issue undoing the command.
     */
    public String undo(Storage storage) throws ProcessingException {
        State prevState = history.getCurrState();
        history.rollBackState();
        State currState = history.getCurrState();
        storage.restoreState(currState);
        return String.format("Your %s command was undone!\nThis is your current list:\n%s",
                prevState.getCommand(),
                storage.displayList());
    }

    /**
     * Redoes the last undone command and restores the next state.
     *
     * @param storage The storage system for managing tasks.
     * @return A message indicating the command was redone and the current list of tasks.
     * @throws ProcessingException If there is an issue redoing the command.
     */
    public String redo(Storage storage) throws ProcessingException {
        history.rollForwardState();
        State currState = history.getCurrState();
        storage.restoreState(currState);
        return String.format("Your %s command was redone!\nThis is your current list:\n%s",
                currState.getCommand(),
                storage.displayList());
    }

    /**
     * Updates the history with a new state, if necessary.
     *
     * @param state The new state to be added to the history.
     */
    public void updateHistory(State state) {
        if (state.isIgnoredHistory()) {
            return;
        }
        history.addState(state);
    }
}
