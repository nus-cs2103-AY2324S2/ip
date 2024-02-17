package duke.state;

import java.util.ArrayDeque;
import java.util.Deque;

import duke.command.Undoable;

/**
 * Represents the history of commands that have been executed.
 */
public class CommandHistory {
    private static final int MAX_HISTORY_SIZE = 100;
    private Deque<Undoable> history;
    private Deque<Undoable> future; // for redo

    /**
     * Constructs a CommandHistory object with the given history and future.
     *
     * @param history The history of commands that have been executed.
     * @param future  The future of commands that have been undone.
     */
    public CommandHistory(Deque<Undoable> history, Deque<Undoable> future) {
        this.history = history;
        this.future = future;
    }

    /**
     * Constructs a CommandHistory object with the default empty history and future.
     */
    public CommandHistory() {
        this.history = new ArrayDeque<>(MAX_HISTORY_SIZE);
        this.future = new ArrayDeque<>(MAX_HISTORY_SIZE);
    }

    /**
     * Adds the given command to the history.
     *
     * @param command The command to be added to the history.
     */

    public void addCommandToHistory(Undoable command) {
        // remove the oldest command if the history size exceeds the limit
        if (history.size() + 1 > MAX_HISTORY_SIZE) {
            history.removeFirst();
        }

        history.addLast(command);
        future.clear(); // clear the future when a new command is added
    }

    /**
     * Returns true if there are commands that can be undone, i.e. there are
     * commands in the history.
     *
     * @return True if there are commands that can be undone, false otherwise.
     */
    public boolean isUndoable() {
        return !history.isEmpty();
    }

    /**
     * Undoes the last command that was executed.
     *
     * @return The last command that was executed, or null if the history is empty.
     */
    public Undoable undo() {
        if (history.isEmpty()) {
            return null;
        }

        Undoable command = history.removeLast();
        future.addLast(command);
        return command;
    }

    /**
     * Redoes the last command that was undone.
     *
     * @return The last command that was undone, or null if the future is empty.
     */
    public Undoable redo() {
        if (future.isEmpty()) {
            return null;
        }

        Undoable command = future.removeLast();
        history.addLast(command);
        return command;
    }
}
