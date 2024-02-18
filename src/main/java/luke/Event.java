package luke;

import java.time.LocalDate;

public class Event extends Task {
    LocalDate startDate;
    LocalDate endDate;

    /**
     * Creates an Event with the specified name, start and end date.
     *
     * @param name The event name.
     * @param startDate The start date.
     * @param endDate The end date.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return The status of task. ([taskType][isDone] taskname start end)
     */
    @Override
    public String getFullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[E][X] ";
        } else {
            checkbox = "[E][ ] ";
        }
        return checkbox + name + " (from: " + startDate +  " to: " + endDate + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Event event = (Event) obj;
        if (event.name.equals(this.name) && event.startDate.equals(this.startDate) && event.endDate.equals(this.endDate) && (event.isDone == this.isDone)) {
            return true;
        }
        return false;
    }
}
