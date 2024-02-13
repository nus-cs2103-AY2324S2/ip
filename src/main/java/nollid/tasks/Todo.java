package nollid.tasks;

import java.util.ArrayList;

/**
 * To-do class represents a task without a specific deadline or duration.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object with the specified description and list of tags.
     *
     * @param description The description of the to-do task.
     * @param tags        The List of tags associated with the Task.
     */
    public Todo(String description, ArrayList<String> tags) {
        super(description, tags);
    }

    /**
     * Overrides the toString method to provide a string representation of the Todo object.
     *
     * @return A formatted string representing the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + super.getTagsString();
    }
}
