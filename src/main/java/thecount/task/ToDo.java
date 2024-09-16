package thecount.task;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

/**
 * Represents a to-do task in the to-do list.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Displays a message related to the to-do task.
     *
     * @param currSize The current size of the list.
     */
    public String displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        return replyToUser.displayMessage();
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Converts the to-do task to a string representation.
     *
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
