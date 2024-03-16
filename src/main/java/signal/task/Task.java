package signal.task;

import java.time.LocalDate;


/**
 * Represents a tasks in available in the Signal chat-bot.
 * A task can be of three types: Todo (T), Deadline (D), or Event (E).
 * Each task has a name and a completion status.
 */
public class Task {
    private String description;
    private boolean isDone = false;
    private boolean isPriority = false;
    private String type;
    private String date;

    /**
     * Constructor for a new Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean checkDone() {
        return this.isDone;
    }
    public boolean checkPriority() {
        return this.isPriority;
    }

    /**
     *
     * @return A representation of the completion of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : "0" ); // mark done task with X
    }

    public String getPriorityIcon() {
        return (this.isPriority ? "* " : ""); // mark priority with P
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    public void setPriority() {
        this.isPriority = true;
    }

    public void setNotPriority() {
        this.isPriority = false;
    }

    public String checkType() {
        return "Task";
    }

    /**
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return " | " + getStatusIcon() + " | " + this.description + " " + getPriorityIcon();
    }


    public LocalDate getDue() {
        return null;
    }

}
