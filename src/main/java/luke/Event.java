package luke;

import java.time.LocalDate;

public class Event extends Task {

    LocalDate start;
    LocalDate end;

    /**
     * Creates an Event with the specified name, start and end date.
     *
     * @param name The event name.
     * @param start The start date.
     * @param end The end date.
     */
    public Event(String name, LocalDate start, LocalDate end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * @return The status of task. ([taskType][isDone] taskname start end)
     */
    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[E][X] ";
        } else {
            checkbox = "[E][ ] ";
        }
        return checkbox + name + " (from: " + start +  " to: " + end + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Event event = (Event) obj;
        if (event.name.equals(this.name) && event.start.equals(this.start) && event.end.equals(this.end) && (event.isDone == this.isDone)) {
            return true;
        }
        return false;
    }
}
