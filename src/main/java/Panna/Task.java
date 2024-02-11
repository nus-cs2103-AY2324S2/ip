package Panna;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The main parent class for each of the tasks!
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    private LocalDate start;
    private LocalDate end;

    private LocalDate deadline;

    protected char taskType;


    // we need to use different constructors for the different classes
    // for todo: only taskName
    // for deadline: taskName + deadline
    // for event: taskName + start + end

    /**
     * Empty constructor to represent a null task
     */
    public Task() {


    }

    /**
     * Represents a ToDo task
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        isDone = false;
        taskType = 'T';
    }

    /**
     * Represents a deadline task
     * @param taskName
     * @param deadline
     */
    public Task(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
        isDone = false;
        taskType = 'D';
    }

    /**
     * Represents an event task
     * @param taskName
     * @param start
     * @param end
     */
    public Task(String taskName, LocalDate start, LocalDate end) {
        this.taskName = taskName;
        this.start = start;
        this.end = end;
        isDone = false;
        taskType = 'E';
    }

    /**
     * Generates the status icon corresponding to if a task is marked or not
     * @return corresponding icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the start time for events
     * @return start timing
     */
    public LocalDate getStart() {
        return tstart;
    }

    /**
     * Gets the end time for events
     * @return end timing
     */
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Gets the deadline time for deadlines
     * @return deadline timing
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Sets the done-ness of the event (marked or unmarked)
     * @param isDone
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Recovers the done-ness of an event
     * @return boolean representing doneness
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * String representation of all tasks
     * @return String representation
     */
    @Override
    public String toString() {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + taskName;
    }
}




