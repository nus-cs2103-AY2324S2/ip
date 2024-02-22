package shodan.tasks.impl;

import shodan.tasks.Task;

/**
 * Represents a basic task with no start and end date.
 */
public class Todo extends Task {
    /**
     * Instantiates a new Todo.
     *
     * @param name the name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
