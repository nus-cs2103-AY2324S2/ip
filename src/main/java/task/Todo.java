package task;

import exception.XiaoBaiException;

/**
 * Represents a todo task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    public Todo(String name) throws XiaoBaiException {
        super(name);
        if (name == null || name.isEmpty() || name == " ") {
            throw new XiaoBaiException("Task name cannot be empty");
        }
    }

    public Todo(String name, boolean doneStatus) throws XiaoBaiException {
        super(name, doneStatus);
        if (name == null || name.isEmpty() || name == " ") {
            throw new XiaoBaiException("Task name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}