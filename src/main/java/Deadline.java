import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by, formattedBy, time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

        String dateAndTime[] = by.split(" ");

        if (dateAndTime.length >= 2) {
            LocalDate d1 = LocalDate.parse(dateAndTime[0]);
            int hour = Integer.parseInt(dateAndTime[1].substring(0, 2));
            int minute = Integer.parseInt(dateAndTime[1].substring(2));
            LocalDateTime dateTime = d1.atTime(hour, minute);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm");
            String formattedString = dateTime.format(formatter);
            this.formattedBy = formattedString;
        } else {
            LocalDate d1 = LocalDate.parse(dateAndTime[0]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
            String formattedString = d1.format(formatter);
            this.formattedBy = formattedString;
        }
    }

    @Override
    public String savedFormat() {
        return "D " + "|" + super.savedFormat() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formattedBy + ")";
    }
}