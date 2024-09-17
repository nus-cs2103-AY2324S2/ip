package entity;

import java.time.LocalDateTime;

/**
 * Abstract class which would be inherited by the various subclasses Deadline, ToDo and Event
 */
public abstract class Task {
    protected String title;
    protected boolean isMarked;

    /**
     * Constructor for Task class that takes in a String as an argument
     * @param title
     */
    Task(String title) {
        this.title = title;
        this.isMarked = false;
    }

    public boolean getMarked() {
        return this.isMarked;
    }

    public void setMarked(boolean markedStatus) {
        this.isMarked = markedStatus;
    }

    public abstract String save();

    public abstract LocalDateTime getDate();

}
