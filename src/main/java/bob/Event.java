package bob;

import java.util.Objects;

/*
 * This class represents an event that can be recorded in the tasklist.
 */
class Event extends Task {
    protected String from;
    protected String to;

    /*
     * A constructor to create a new event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /*
     * A method to get from.
     *
     * @return The start date of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /*
     * A method to get to.
     *
     * @return The end date of the event.
     */
    public String getTo() {
        return this.to;
    }

    /*
     * A method that returns the status of the task.
     *
     * @return A label [E] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /*
     * A method to check if two objects are equal.
     *
     * @parameter o The object to compare to.
     * @return A boolean representing whether the two objects are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Event event = (Event) o;

        return Objects.equals(from, event.from) &&
                Objects.equals(to, event.to);
    }
}