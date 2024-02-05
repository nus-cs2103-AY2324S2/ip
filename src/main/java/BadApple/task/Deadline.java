package BadApple.task;

import BadApple.main.BadAppleException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task with a Date to indicate
 * when a task should be completed. It can also
 * extract details to fill its relevant fields
 */
public class Deadline extends Task {
    protected LocalDate by;
    private Deadline(String desc, String by) {
        super(desc);
        this.by = LocalDate.parse(by);
    }

    /**
     * The String a call to deadline returns
     * @return The task with deadline formatted to be readable by parser
     */
    @Override
    public String toString() {
        return "Deadline" + " " + super.toString() +
                "(by: " + by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " )";
    }

    /**
     * This is a Factory Method that generates an instance
     * @param s an ArrayList after tokenizing the query.
     */
    public static Deadline extractDetails(ArrayList<String> s) throws BadAppleException, DateTimeParseException {
        StringBuilder taskName = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        int separatorIndex = s.indexOf("/by");
        if (separatorIndex > 0) {
            for (int i = 1; i < separatorIndex; i++) {
                taskName.append(s.get(i)).append(" ");
            }
            for (int i = separatorIndex + 1; i < s.size();i++) {
                deadline.append(s.get(i));
            }
            return new Deadline(taskName.toString(), deadline.toString());
        } else {
            throw new BadAppleException("Usage: deadline TaskName /by DueTime");
        }

    }

    // in case anyone tries to throw an un-formatted string, the program still runs
    public static Deadline extractDetails(String s) {
        return extractDetails(new ArrayList<>(Arrays.asList(s.split(" "))));
    }
}
