import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskCategory category;
    protected String from;
    protected String to;
    protected String by;

    public Task(String description, TaskCategory cat) {
        this.description = description;
        this.isDone = false;
        this.category = cat;
    }

    // update task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // update status of task as not done
    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public TaskCategory getTaskCat() {
        return category;
    }

    // setter event
    public Task setFrom(String from) {
        this.from = from;
        return this;
    }

    // setter event
    public Task setTo(String to) {
        this.to = to;
        return this;
    }

    // setter deadline
    public Task setBy(String by) {
        this.by = by;
        return this;
    }

    // getter event
    public String getFrom() {
        return from;
    }

    // getter event
    public String getTo() {
        return to;
    }

    // getter deadline
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[" + category + "]" + getStatusIcon() + getDescription() + category.getDetails(this);
    }

    // Enumeration for task categories
    public enum TaskCategory {
        T { // Todo
            @Override
            public String getDetails(Task task) {
                return "";
            }
        },
        E { // Events
            @Override
            public String getDetails(Task task) {
                return " (from: " + task.getFrom() + " to: " + task.getTo() + ")";
            }
        },
        D { // Deadline
            @Override
            public String getDetails(Task task) {
                return " (by: " + task.getBy() + ")";
            }
        };
        public abstract String getDetails(Task task);
    }
}
