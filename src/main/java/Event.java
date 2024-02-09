import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description) throws DukeException{
        this.fullTaskDescription = description;
        try {
            String[] command = description.split(" /from ", 2);
            if (command.length <= 1) {
                throw new DukeException(" OOPS! Your Only Friend cannot take in an event entry with no timeline :(\n");
            }

            String timeline = command[1];
            String[] eventDates = timeline.split(" /to ");
            if (eventDates.length <= 1) {
                throw new DukeException(" OOPS! Your Only Friend cannot take in an event entry with no timeline :(\n");
            }

            this.from = LocalDateTime.parse(eventDates[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.to = LocalDateTime.parse(eventDates[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.description = command[0];

        } catch (DateTimeParseException e) {
            throw new DukeException(" OOPS! Please enter deadline in a valid format (yyyy-mm-dd HH:mm). :(\n");
        }
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + this.description + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String toSave() {
        return " E" + (super.getStatusIcon().equals("X") ? " | 1 | " : " | 0 | ") + this.fullTaskDescription;
    }

}
