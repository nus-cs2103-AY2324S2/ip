package Irwyn.Tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    TaskType task;

    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.task = taskType;
    }
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description; // Override toString method to print task status
    }

    public String replyString (int numberList) {
        return "Got it. I've added this task:\n  "
                + this + "\n"
                + "Now you have " + numberList + " tasks in the list.\n";
    }
}
