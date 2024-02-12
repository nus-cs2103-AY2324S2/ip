package rick.tasks;
import rick.RickException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Item {
    private String name;
    private String status;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean includeTime;

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
            this.includeTime = !(from.length() == 10 && to.length() == 10);
            this.from = from.length() == 10 ? LocalDateTime.parse(from + "T00:00:00")
                    : LocalDateTime.parse(from);
            this.to = to.length() == 10 ? LocalDateTime.parse(to + "T00:00:00")
                    : LocalDateTime.parse(to);
        } catch (Exception e) {
            throw new RickException("Something wrong with your input! " +
                    "Follow 'event [event] /by yyyy-mm-ddTHH:mm:ss'");
        }
    }
    @Override
    public String toString(){
        if (this.includeTime) {
            return "[E]" + this.status + " " + this.name +
                    " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) +
                    " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
        } else {
            return "[E]" + this.status + " " + this.name +
                    " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                    " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
    public void mark() {
        this.status = "[X]";
    }
    public void unmark() {
        this.status = "[ ]";
    }
    public String store() {
        return "E|" + this.status + "|" + this.name + "|" + this.from + "|" + this.to;
    }
}
