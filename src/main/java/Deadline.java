import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    LocalDate deadline;
    public Deadline(String name, String deadline) throws InvalidCommandException{
        super(name);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeException e) {
            throw new InvalidCommandException(
                    "Invalid input format for date :( Please use the format yyyy-mm-dd instead!");
        }
    }

    @Override
    public String toString() {
        String formattedDate = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("%s %s (by: %s)", "[D]", super.toString(), formattedDate);
    }

    @Override
    public String dataString() {
        return String.format("%s|%s|%s\n", "D", super.dataString(), deadline);
    }
}
