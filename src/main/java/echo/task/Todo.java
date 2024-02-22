/*
 * Package: Echo.Task
 * Module/Project Name: Echo
 * File: Todo.java
 *
 * Description:
 * This class represents a Todo task, a specific type of task without a due date.
 * It extends the Task class.
 *
 */

package echo.task;

public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Gets the task type of the Todo task.
     *
     * @return The task type code for the Todo task ("T").
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a string representation of the Todo task for saving to a file.
     *
     * @return A formatted string representation of the Todo task for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s",
                getTaskType(),
                isDone() ? 1 : 0,
                getDescription());
    }
}
