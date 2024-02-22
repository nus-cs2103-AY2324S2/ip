// Task.java
public class Task {
    private static int nextTaskNumber = 1;

    private int taskNumber;
    private String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        // Check if the task is newly created or loaded from storage
        if (nextTaskNumber == 1) {
            // Newly created task, assign the next task number
            this.taskNumber = nextTaskNumber++;
        } else {
            // Loaded from storage, keep the existing task number
            this.taskNumber = nextTaskNumber;
        }

        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public static void resetNextTaskNumber() {
        nextTaskNumber = 1;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "|" + (isDone ? " 1 " : " 0 ") + "| " + taskDescription;
    }
}
