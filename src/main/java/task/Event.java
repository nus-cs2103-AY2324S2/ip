package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for an Event.
     * @param description Description of the Event.
     * @param from DateTime object to represent from DateTime.
     * @param to DateTime object to represent to DateTime.
     * */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for an Event.
     * @param description Description of the Event.
     * @param isDone Boolean value to state if the task is done or not.
     * @param from DateTime object to represent from DateTime.
     * @param to DateTime object to represent to DateTime.
     * */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Prints the task in the console.
     * */
    @Override
    public void taskPrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + "[E][ ]" + " " + description + "(from: " + from.format(output) + " to: " + to.format(output) +")";
        System.out.println(result);
    }

    /**
     * Prints the task and its corresponding index in the list of tasks in the console.
     * */
    @Override
    public void taskPrinter(int index) {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + (index+1) + ".[E]" + getStatusIcon() + " " + description  + "(from: " + from.format(output) + " to: " + to.format(output) +")";
        System.out.println(result);
    }

    /**
     * Prints the task in the format of the saved txt file.
     * */
    @Override
    public String storagePrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return "E" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + "|from" + from.format(output) + "|to" + to.format(output);
    }

    /**
     * Checks if an Event is equal to the current instance of Event.
     *
     * @param obj The object that is to be checked for equality with current Event object.
     * @return True if the obj is equal else return False.
     * */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof Event) {
            Event event = (Event) obj;
            return from.equals(event.from) && to.equals(event.to);
        }

        return false;
    }
}
