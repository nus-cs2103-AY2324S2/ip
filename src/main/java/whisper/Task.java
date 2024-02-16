package whisper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskCategory category;
    protected String from;
    protected String to;
    protected String by;
    protected LocalDateTime deadline;
    protected LocalDateTime eventFromDateTime;
    protected LocalDateTime eventToDateTime;

    // Constuctor for each task category
    public Task(String description, TaskCategory cat) {
        this.description = description;
        this.isDone = false;
        this.category = cat;
    }

    // Deadline constructor
    public Task(String description, TaskCategory cat, LocalDateTime deadline) {
        this(description, cat);
        this.deadline = deadline;
    }

    // Event constructor
    public Task(String description, TaskCategory cat, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this(description, cat);
        this.eventFromDateTime = fromDateTime;
        this.eventToDateTime = toDateTime;
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

    // getter for date and time
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getEventFromDateTime() {
        return eventFromDateTime;
    }

    public LocalDateTime getEventToDateTime() {
        return eventToDateTime;
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

    // format datetime details for all task categories
    private String formatDateTime() {
        if (category == TaskCategory.D && deadline != null) {
            return " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + ")";
        } else if (category == TaskCategory.E && eventFromDateTime != null && eventToDateTime != null) {
            return " (from: " + eventFromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) +
                    " to: " + eventToDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + ")";
        }
        return "";
    }

    @Override
    public String toString() {
        return "[" + category + "]" + getStatusIcon() + getDescription()
                + category.getDetails(this);
    }

    public enum TaskCategory {
        T {
            @Override
            public String getDetails(Task task) {
                return "";
            }
        },
        E {
            @Override
            public String getDetails(Task task) {
                return " (from: " + task.getEventFromDateTime().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                        + " to: " + task.getEventToDateTime().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                        + ")";
            }
        },
        D {
            @Override
            public String getDetails(Task task) {
                if (task.getDeadline() != null) {
                    return " (by: " + task.getDeadline().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
                } else {
                    return "";
                }
            }
        };

        public abstract String getDetails(Task task);
    }

}
