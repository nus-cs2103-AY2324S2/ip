package duke.task;

import java.util.Objects;

/**
 * The class representing a generic task.
 * */
public class Task {
    /* Indicator for unmarked task. */
    public static final String UNMARKED_INDICATOR = "[ ]";
    /* Indicator for marked task. */
    public static final String MARKED_INDICATOR = "[X]";
    /* Indicator for unmarked task. */
    public static final String DB_UNMARKED_INDICATOR = "0";
    /* Indicator for marked task. */
    public static final String DB_MARKED_INDICATOR = "1";
    /* Description of the current task. */
    private final String description;
    /* Completion status of the current task. */
    private boolean isDone;
    /* Mark reflecting completion status for the current task. */
    private String display;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.display = UNMARKED_INDICATOR;
    }

    /**
     * Changes the display (completion status) from 'done' to 'undone' and vice-versa.
     * */
    public void changeDone() {
        this.isDone = !this.isDone;
        if (this.isDone) {
            this.display = MARKED_INDICATOR;
        } else {
            this.display = UNMARKED_INDICATOR;
        }
    }

    /**
     * Getter method for task description.
     *
     * @return The task description.
     * */
    public String getDescription() {
        return description;
    }

    /**
     * Getter method for task display.
     *
     * @return The task description.
     * */
    public String getDisplay() {
        return display;
    }

    /**
     * Converts the task into the form to be stored in db.txt.
     *
     * @return The formatted string.
     * */
    public String toDbString() {
        String display;
        if (Objects.equals(getDisplay(), UNMARKED_INDICATOR)) {
            display = DB_UNMARKED_INDICATOR;
        } else {
            display = DB_MARKED_INDICATOR;
        }
        return "|" + display + "|" + getDescription() + "|";
    }
}
