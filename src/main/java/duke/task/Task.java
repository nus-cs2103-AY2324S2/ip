package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {

    /**
     * Description of duke.command.task as a string
     */
    private String description;

    /**
     * Boolean Flag of whether the duke.command.task is done
     */
    private boolean isDone;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs duke.command.task with specified description.
     *
     * @param description Description of duke.command.task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a duke.command.task with done flag specified as a string. This method is used for storage activities.
     *
     * @param description Brief description of duke.command.task.
     * @param isDone      String representing boolean value.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets this duke.command.task as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets this duke.command.task as not done
     */
    public void setNotDone() {
        this.isDone = false;

    }

    @Override
    public String toString() {
        String mark = this.isDone ? "[X] " : "[ ] ";
        return mark + this.description;
    }

    public static DateTimeFormatter getDateFormat() {
        return Task.DATE_FORMATTER;
    }

    /**
     * Returns a string containing information of duke.command.task in a clean machine-readable format
     * "{description},{isDone}"
     *
     * @return string with tokens separated by space
     */
    public String getTokens() {
        return String.join(",", this.description, String.valueOf(this.isDone));
    }

}





