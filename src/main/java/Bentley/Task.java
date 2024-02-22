// Task.java
package Bentley;

public class Task {
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public String doneOrNot() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return "|" + doneOrNot() + "| " + taskDescription;
    }

}