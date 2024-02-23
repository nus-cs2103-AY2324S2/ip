package task;

import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String name, boolean isDone, LocalDateTime from, LocalDateTime to){
        super(name, isDone);
        this.from = from;
        this.to = to;
    }


    public LocalDateTime getTo(){
        return this.to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    @Override
    public String toString() {

        return "[E] " + super.toString() + " (from:" + from.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + " to:" + to.format(DateTimeFormatter.ofPattern("MMM dd yyy HH:mm")) + ")" ;
    }
    @Override
    public String toFileString() {
        return "E" + " | " + getStatusNum() + " | " + this.name + " | " + from.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm")) + " - " + to.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm"));
    }
}