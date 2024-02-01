package duke;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description, Task.TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
//        String[] arr1 = from.split(" ", 2);
//        String[] arr2 = to.split(" ", 2);
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String getType(){
        return "E";
    }
    public String toFileString(){
        return String.format("%s |  %d | %s | %s | %s", getType(), isDone? 1:0, description, from, to);
    }
}