package arc.tasks;

import java.util.ArrayList;

import arc.Arc;
import arc.exceptions.tasks.EmptyDescriptionException;

/**
 * Represents a Todo task in the Arc application.
 * A Todo task is a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     * The task is initially not completed.
     *
     * @param description The description of the Todo task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    /**
     * Constructs a new Todo task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isDone The completion status of the task.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Todo(String description, boolean isDone) throws EmptyDescriptionException {
        super(description, isDone);
    }

    /**
     * Serializes the Todo task to a string format for storage.
     * This includes the task type, description, and completion status.
     *
     * @return A string representing the serialized form of the Todo task.
     */
    @Override
    public String serialize() {
        ArrayList<String> taskArgs = new ArrayList<>();

        taskArgs.add("todo");
        taskArgs.add(this.getDescription());
        taskArgs.add(this.isDone() ? "1" : "0");

        return String.join(Arc.ARG_DELIMITER, taskArgs);
    }

    /**
     * Returns a string representation of the Todo task, including its status icon and description.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
