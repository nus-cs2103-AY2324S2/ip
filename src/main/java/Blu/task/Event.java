package blu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String TASK_TYPE = "E";
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    private String displayDate(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return by.format(formatter);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s", TASK_TYPE, this.getIsMarked() ? "T" : "F",
                this.getTitle(), this.from, this.to);
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s (From: %s To: %s)", TASK_TYPE, super.toString(), 
                displayDate(this.from), displayDate(this.to));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return this.from.equals(other.from) && this.to.equals(other.to) && super.equals(other);
    }
}
