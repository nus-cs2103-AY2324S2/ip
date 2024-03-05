package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task that has a start and end time
 * Extends the Task class
 */

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Instantiates an Event object
     * @param name Describes the task
     * @param isDone A boolean that indicates if the task is completed
     * @param from The start time of the event
     * @param to The end time of the event
     */
    public Event(String name, boolean isDone, LocalDateTime from, LocalDateTime to){
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event
     * @return The end time of the event
     */
    public LocalDateTime getTo(){
        return this.to;
    }

    /**
     * Gets the start time of the event
     * @return the start time of the event
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gives a string representation of the event object
     * @return the type, status , start and end time of the event
     */
    @Override
    public String toString() {

        return "[E] " + super.toString() + " (from:" + from.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + " to:" + to.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ")" ;
    }

    /**
     * Gives a string representation of the event object in a format to be stored
     * @return the type, status represented as a number , start and end time of the event
     */
    @Override
    public String toFileString() {
        return "E" + " | " + getStatusNum() + " | " + this.name + " | " + from.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm")) + " - " + to.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm"));
    }
}