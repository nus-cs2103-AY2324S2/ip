package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class for managing all kinds of Tasks.
 */
public abstract class Task {
    /** The task list, update upon creation of task */
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param text the description of the task
     */
    public Task(String text) {
        this.description=text;
        this.isDone=false;
    }

    /**
     * @return if task is Done, we cross it out.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark task with X
    }

    /**
     * @return if task is Done, return true.
     */
    public Boolean getStatus() {return isDone;}

    /**
     * Return the description of task.
     * @return return the description of task.
     */
    public String getDescription() {return this.description;}

    /**
     * abstract method
     * @return return T/D/E.
     */
    public abstract String getTaskTypeIcon();

    /**
     * abstract method
     * @return return todo/event/deadline.
     */
    public abstract String getTaskType();

    /**
     * Whether we have to start doing it
     * @param current current time
     * @return yes/no
     */
    public abstract boolean isTimeForStart(LocalDate current);

    /**
     * Change the status of task from not Done to Done
     */
    public void Done() {
        this.isDone=true;
    }

    /**
     * Change the status of task from not Done to Done
     */
    public void unDone() {
        this.isDone=false;
    }
    /**
     *
     * @return the String representation of duke.task.Task
     */
    @Override
    public String toString() {
        String temp = String.format("[%s][%s] %s", this.getTaskTypeIcon(), this.getStatusIcon(), this.description);
        return temp;
    }

}
