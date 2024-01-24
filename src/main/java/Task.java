public class Task {
    protected String description;
    protected boolean isDone;

    protected static int taskCount;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public static void addTask() {
        taskCount++;
    }
    public static String numOfTask() {
        return "Now you have " + taskCount + " tasks in the list.";
    }
}

