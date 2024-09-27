package rick.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import rick.logic.RickException;

/**
 * The event tasks.
 */
public class Event implements Task {
    private String name;
    private String status;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean includesTime;

    /**
     * Creates a new Event Item instance with specified name, status and date/time.
     * @param name the name of the Item.
     * @param status the status of the Item, either done or not done.
     * @param from the starting date or time, written in specified string format.
     * @param to the ending date or time, written in specified string format.
     * @throws RickException when there is a problem with user input
     */
    public Event(String name, String status, String from, String to) throws RickException {
        try {
            if (name.isBlank()) {
                throw new RickException("Nothing scheduled?");
            }
            if (from.isBlank()) {
                throw new RickException("from when?");
            }
            if (to.isBlank()) {
                throw new RickException("to when?");
            }
            this.name = name;
            this.status = status;
            this.includesTime = !(from.length() == 10 && to.length() == 10);
            this.from = from.length() == 10
                    ? LocalDateTime.parse(from + "T00:00:00")
                    : LocalDateTime.parse(from);
            this.to = to.length() == 10
                    ? LocalDateTime.parse(to + "T00:00:00")
                    : LocalDateTime.parse(to);
        } catch (Exception e) {
            throw new RickException("Something wrong with your input! "
                    + "Follow 'event [event] /by yyyy-mm-ddTHH:mm:ss'");
        }
    }

    /**
     * Checks if two events conflict with each other.
     * @param event1 the event to be checked.
     * @param event2 another event to be checked.
     * @throws RickException if two event clashes
     */
    public static void checkClash(Event event1, Event event2) throws RickException {
        Event earlyEvent;
        Event lateEvent;
        if (event1.from.isBefore(event2.from)) {
            earlyEvent = event1;
            lateEvent = event2;
        } else {
            earlyEvent = event2;
            lateEvent = event1;
        }
        if (lateEvent.from.isBefore(earlyEvent.to)) {
            throw new RickException("Event " + earlyEvent.name + " clashes with " + lateEvent.name);
        }
    }

    /**
     * Returns the string representation for the Event item that is understandable for the user.
     * @return a user-friendly string representation for the item.
     */
    @Override
    public String toString() {
        if (this.includesTime) {
            return "[E]" + this.status + " " + this.name
                    + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss"))
                    + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
        } else {
            return "[E]" + this.status + " " + this.name
                    + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }

    /**
     * Marks the item as done.
     */
    public void mark() {
        this.status = "[X]";
    }

    /**
     * Unmarks the item as not yet done.
     */
    public void unmark() {
        this.status = "[ ]";
    }

    /**
     * Returns the item in a string format that is compatible with the local data file.
     * @return a string representation of the item in a particular format.
     */
    public String store() {
        return "E|" + this.status + "|" + this.name + "|" + this.from + "|" + this.to;
    }
}
