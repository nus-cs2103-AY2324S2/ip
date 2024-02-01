package leto.tasklist;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private LocalDate deadline;

    private Deadline(Boolean completed, String TaskString, LocalDate deadline) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, TaskString);
        this.deadline = deadline;
    }

    public static Deadline DeadlineFromCMD(String input) throws InvalidTaskException {
        String regex = "(?i)deadline ([^,]+) /by (\\d{4}-\\d{2}-\\d{2})";
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (!matcher.matches()) {
            throw new InvalidTaskException("deadline <description> /by <date>,"
                    + " <date> should be in the format YYYY-MM-DD.");
        }
        LocalDate deadline = LocalDate.parse(matcher.group(2).trim());
        return new Deadline(false, matcher.group(1).trim(), deadline);
    }

    private String remainingDaysMessage() {
        long remainingDays = ChronoUnit.DAYS.between(LocalDate.now(), this.deadline);
        if (remainingDays == 0) {
            return "due today";
        } else if (remainingDays < 0) {
            return Math.abs(remainingDays) + " days past deadline";
        } else {
            return remainingDays + " days remaining";
        }
    }

    /**
     * Create a Deadline task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return a Deadline task
     */
    public static Deadline DeadlineFromCSV(String entry) throws InvalidTaskException {
        String regex = "([DTE]),([YN]),([^,]*),([^,]*),([^,]*),([^,]*)(\\n?)";
        Matcher matcher = Pattern.compile(regex).matcher(entry);
        if (!matcher.matches()) {
            throw new InvalidTaskException("Cannot match " + entry + " with regex");
        }
        Boolean completed = matcher.group(2).equals("Y");
        String message = matcher.group(3);
        LocalDate deadline = LocalDate.parse(matcher.group(4));
        return new Deadline(completed, message, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")"
                + " " + this.remainingDaysMessage();
    }

    /**
     * Returns the object as a row in a csv table according to format
     * Type,Completed,Task,By,From,To
     * @return String in csv format
     */
    public String toCSVString() {
        return "D," + super.toCSVString() + "," + this.deadline.toString() + ",,";
    }
}
