package me.ruibin.leto.tasklist;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** A type of Task with a deadline LocalDate field. */
public class Deadline extends Task {
    private LocalDate deadline;

    private Deadline(Boolean completed, String taskString, LocalDate deadline) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, taskString);
        this.deadline = deadline;
    }

    /**
     * Extends the deadline by a specified number of days.
     *
     * @param days The number of days to extend the deadline.
     */
    public void extendDeadline(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Cannot extend deadline by a negative number of or 0 days.");
        }
        this.deadline = this.deadline.plusDays(days);
    }

    /**
     * Read user input and generate a new instance of Deadline.
     *
     * @param input Command inputs from user.
     * @return A new instance of Deadline.
     * @throws InvalidTaskException If input do not fit valid command format.
     */
    public static Deadline deadlineFromCommand(String input) throws InvalidTaskException {
        String regex = "(?i)deadline ([^,]+) /by (\\d{4}-\\d{2}-\\d{2}) *";
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
     * Create a Deadline task from csv format, Type,Completed,Task,By,From,To.
     *
     * @param entry text string containing the row in the csv
     * @return a Deadline task
     */
    public static Deadline deadlineFromCsv(String entry) throws InvalidTaskException {
        String regex = "([DTE]),([YN]),([^,]*),([^,]*),([^,]*),([^,]*)(\\n?)";
        Matcher matcher = Pattern.compile(regex).matcher(entry.trim());
        if (!matcher.matches()) {
            throw new InvalidTaskException("Cannot match " + entry + " with regex");
        }
        Boolean completed = matcher.group(2).equals("Y");
        String message = matcher.group(3);
        try {
            LocalDate deadline = LocalDate.parse(matcher.group(4));
            return new Deadline(completed, message, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("By field, [" + matcher.group(4) + "] is invalid!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")"
                + " " + this.remainingDaysMessage();
    }

    /**
     * Returns the object as a row in a csv table according to format:<br>
     * <code>Type,Completed,Task,By,From,To</code>.
     *
     * @return String in csv format
     */
    public String toCsvString() {
        return "D," + super.toCsvString() + "," + this.deadline.toString() + ",,";
    }
}
