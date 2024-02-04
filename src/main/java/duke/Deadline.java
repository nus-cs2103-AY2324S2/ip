package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static Ui ui = new Ui();
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Invalid date format. Please use dd/MM/yyyy HHmm format. Example: 02/12/2019 1800\n");
        }
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "D | " + super.toString() + " | " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    public static Deadline fromString(String input) {
        String[] split = input.split(" \\| ");
        try {
            Deadline deadline = new Deadline(split[2], split[3]);
            if (split[1].equals("X")) {
                deadline.markAsDone();
            }
            return deadline;
        } catch (DukeException e) {
            ui.printMessage("Error creating deadline");
            return null;
        }
    }

    public String toFileString() {
        return "D | " + super.toString() + " | " + by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}