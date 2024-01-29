/**
 * Task - Represents a basic task with a description and completion status.
 */

public class Task {
    protected String userInput;
    protected boolean isDone;

    public Task(String input) {
        this.userInput = input;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ?  "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + userInput;
    }
}