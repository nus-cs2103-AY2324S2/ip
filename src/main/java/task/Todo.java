package task;

import exception.XiaoBaiException;

/**
 * Represents a todo task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given name.
     *
     * @param name The name of the todo task.
     * @throws XiaoBaiException if the task name is null, empty, or contains only
     *                          whitespace.
     */
    public Todo(String name) throws XiaoBaiException {
        super(name);
    }

    /**
     * Constructs a Todo task with the given name and done status.
     *
     * @param name       The name of the todo task.
     * @param doneStatus The status of the todo task (true if done, false
     *                   otherwise).
     * @throws XiaoBaiException if the task name is null, empty, or contains only
     *                          whitespace.
     */
    public Todo(String name, boolean doneStatus) throws XiaoBaiException {
        super(name, doneStatus);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}