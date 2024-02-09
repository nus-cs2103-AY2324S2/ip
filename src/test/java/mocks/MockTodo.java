package mocks;

import tasks.Task;

/**
 * The MockTodo class represents a mock implementation of a Todo task for testing purposes.
 */
public class MockTodo extends Task {
    private boolean isDone;

    /**
     * Constructs a MockTodo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public MockTodo(String description) {
        super(description);
        this.isDone = false;
    }

    /**
     * Marks the mock todo task as done.
     */
    @Override
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the mock todo task as not done.
     */
    @Override
    public void unmark() {
        isDone = false;
    }

    /**
     * This method is not used in mocks, hence returns an empty string.
     */
    @Override
    public String convertTaskToFileString() {
        return "";
    }

    /**
     * Retrieves the completion status of the mock todo task.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    @Override
    public boolean isDone() {
        return isDone;
    }
}
