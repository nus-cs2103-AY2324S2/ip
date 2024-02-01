import java.util.Arrays;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description) throws DukeException{
        String[] command = description.split(" /from ", 2);
        if (command.length <= 1) {
            throw new DukeException("____________________________________________________________\n" +
                    "OOPS! Your Only Friend cannot take in an event entry with no timeline :(\n" +
                    "____________________________________________________________\n");
        }

        String timeline = command[1];
        String[] eventDates = timeline.split(" /to ");
        if (eventDates.length <= 1) {
            throw new DukeException("____________________________________________________________\n" +
                    "OOPS! Your Only Friend cannot take in a deadline entry with no timeline :(\n" +
                    "____________________________________________________________\n");
        }

        this.from = eventDates[0];
        this.to = eventDates[1];

        this.description = command[0];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.description + " (from: " + this.from + " to: " + this.to + ")";
    }

}
