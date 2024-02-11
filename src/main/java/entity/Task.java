package entity;

import java.time.LocalDateTime;

/**
 * Abstract class which would be inherited by the various subclasses Deadline, ToDo and Event
 */
public abstract class Task {
    protected String title;
    protected boolean marked;

    /**
     * Constructor for Task class that takes in a String as an argument
     * @param title
     */
    Task(String title) {
        this.title = title;
        this.marked = false;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public void setMarked(boolean bool) {
        this.marked = bool;
    }

    public abstract String save();

    public abstract LocalDateTime getDate();

}
