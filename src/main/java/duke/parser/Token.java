package duke.parser;

import duke.storage.Task;

/**
 * The Token class represents a parsed command along with associated data.
 * It is used to convey information between the Parser and other components of the Duke application.
 */
public class Token {
    private Command cmd;
    private Task task;
    private int selectedItem;

    /**
     * Constructs a Token object with the specified command.
     *
     * @param cmd The command associated with the token.
     */
    public Token(Command cmd) {
        this.cmd = cmd;
    }

    /**
     * Constructs a Token object with the specified command and task.
     *
     * @param cmd The command associated with the token.
     * @param task The task associated with the token.
     */
    public Token(Command cmd, Task task) {
        this.cmd = cmd;
        this.task = task;
    }

    /**
     * Constructs a Token object with the specified command and selected item index.
     *
     * @param cmd The command associated with the token.
     * @param selectedItem The index of the selected item.
     */
    public Token(Command cmd, int selectedItem) {
        this.cmd = cmd;
        this.selectedItem = selectedItem;
    }

    /**
     * Gets the command associated with the token.
     *
     * @return The command associated with the token.
     */
    public Command getCmd() {
        return this.cmd;
    }

    /**
     * Gets the index of the selected item associated with the token.
     *
     * @return The index of the selected item.
     */
    public int getSelectedItem() {
        return this.selectedItem;
    }

    /**
     * Gets the task associated with the token.
     *
     * @return The task associated with the token.
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Sets the command of the token as "SAVED."
     * This is used to indicate that the associated task has been successfully saved.
     */
    public void setAsSaved() {
        this.cmd = Command.SAVED;
    }

}