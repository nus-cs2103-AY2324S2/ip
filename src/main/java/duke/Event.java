package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static Ui ui = new Ui();

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.to = LocalDateTime.parse(to,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Invalid date format. Please use dd/MM/yyyy HHmm format. Example: 02/12/2019 1800\n");
        }
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + " | "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    public static Event fromString(String input) {
        String[] split = input.split(" \\| ");
        try {
            Event event = new Event(split[2], split[3], split[4]);
            if (split[1].equals("X")) {
                event.markAsDone();
            }
            return event;
        } catch (DukeException e) {
            ui.printMessage("Error creating event");
            return null;
        }
    }

    public String toFileString() {
        return "E | " + super.toString() + " | "
                + from.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + " | "
                + to.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}