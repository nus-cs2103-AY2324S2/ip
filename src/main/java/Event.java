import java.util.Arrays;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) {
        String[] command = description.split(" /from ", 2);
        String timeline = command[1];
        String[] eventDates = timeline.split(" /to ");

        this.from = eventDates[0];
        this.to = eventDates[1];

        this.description = command[0];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.description + " (from: " + this.from + " to: " + this.to + ")";
    }

}
