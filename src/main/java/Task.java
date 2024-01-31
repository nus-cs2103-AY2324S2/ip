import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class Task {
    protected String description;
    protected int isDone;

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String stringToDate(String input) {
        try {
            LocalDate ld = LocalDate.parse(input);
            String date = ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter a date in yyyy-MM-dd format.");
            return null;
        }
    }

    public String getStatusIcon() {
        if (isDone == 0) {
            return " ";
        } else {
            return "X";
        }
    }

    public void markAsDone() {
        this.isDone = 1;
        System.out.println("This task is marked as done:\n"
                + this);
    }

    public void markAsUndone() {
        this.isDone = 0;
        System.out.println("This task is marked as not done yet:\n"
                + this);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
