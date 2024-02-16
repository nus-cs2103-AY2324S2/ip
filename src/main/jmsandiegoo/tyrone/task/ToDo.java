package jmsandiegoo.tyrone.task;

/**
 * Represents the todo task of the application.
 */
public class ToDo extends Task {
    
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String taskTypeStr = "[T] ";
        return taskTypeStr
                + super.toString();
    }

    @Override
    public Task copy() {
        ToDo todoCopy = new ToDo(super.description);
        todoCopy.isDone = super.isDone;

        return todoCopy;
    }

    @Override
    public String serializeTask() {
        String taskTypeSerializedStr = "T | ";
        return taskTypeSerializedStr
                + super.serializeTask();
    }
}