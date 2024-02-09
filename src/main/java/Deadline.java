import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String deadline;
    private LocalDateTime deadlineDateFormat;
    public Deadline(String description) throws DukeException {
        this.fullTaskDescription = description;
        try {
            String[] command = description.split(" /by ");
            if (command.length <= 1) {
                throw new DukeException(" OOPS! Your Only Friend cannot take in a deadline entry with no time :(\n");
            }
            this.deadlineDateFormat = LocalDateTime.parse(command[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.description = command[0];
        } catch (DateTimeParseException e) {
            throw new DukeException(" OOPS! Please enter deadline in a valid format (yyyy-mm-dd HH:mm). :(\n");
        }

    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + this.description + " (by: " + deadlineDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    @Override
    public String toSave() {
        return " D" + (super.getStatusIcon().equals("X") ? " | 1 | " : " | 0 | ") + this.fullTaskDescription;
    }
}
