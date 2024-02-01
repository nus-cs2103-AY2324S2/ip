package leto.tasklist;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static leto.ui.Ui.letoSpeak;
import static leto.ui.Ui.shortSay;

public class Deadline extends Task {
    private LocalDate deadline;

    private Deadline(Boolean completed, String TaskString, LocalDate deadline) {
        // Call to 'super()' must be first statement in constructor body
        super(completed, TaskString);
        this.deadline = deadline;
    }

    public static Deadline DeadlineFactory(String message) throws InvalidTaskException {
        String regex = "deadline ([^,]+) /by (\\d{4}-\\d{2}-\\d{2})";
        Matcher matcher = Pattern.compile(regex).matcher(message);
        if (!matcher.matches()) {
            throw new InvalidTaskException("deadline <description> /by <date>,"
                    + " <date> should be in the format YYYY-MM-DD.");
        }
//        message = message.replaceFirst("deadline ", "");
//        String[] messageParts = message.split(" /by ");
//        if (messageParts.length < 2) {
//            throw new InvalidTaskException("Task need to follow\n   `deadline _task_ /by _time_` format");
//        }
        LocalDate deadline = LocalDate.parse(matcher.group(2).trim());
        return new Deadline(false, matcher.group(1).trim(), deadline);
    }

    /**
     * Create a Deadline task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return a Deadline task
     */
    public static Deadline DeadlineFromCSV(String entry) throws InvalidTaskException {
//        entry = "D,N,1,2023-04-15,,";
//        letoSpeak("Testing overriden entry");
        String regex = "([DTE]),([YN]),([^,]*),([^,]*),([^,]*),([^,]*)(\\n?)";
        Matcher matcher = Pattern.compile(regex).matcher(entry);
        if (!matcher.matches()) {
            throw new InvalidTaskException("Cannot match " + entry + " with regex");
        }
        Boolean completed = matcher.group(2).equals("Y");
        String message = matcher.group(3);
        LocalDate deadline = LocalDate.parse(matcher.group(4));
//        String[] parts = entry.split(",");
//        Boolean completed = parts[1].equals("Y");
//        String message = parts[2];
//        String deadlineString = parts[3];
        return new Deadline(completed, message, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
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
