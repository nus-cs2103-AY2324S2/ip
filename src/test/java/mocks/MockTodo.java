package mocks;

import tasks.Task;

public class MockTodo extends Task {
    private boolean isDone;

    public MockTodo(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public void mark() {
        isDone = true;
    }

    @Override
    public void unmark() {
        isDone = false;
    }

    @Override
    public String convertTaskToFileString() {
        return "";
    }

    @Override
    public boolean getIsDone() {
        return isDone;
    }
}
