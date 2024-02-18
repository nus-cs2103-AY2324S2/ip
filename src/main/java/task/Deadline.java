/**
 * This is a Deadline Task.
 * It is a task that contains a deadline.
 */

package task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;
    protected LocalDateTime deadline;

    public Deadline(String description, String by) throws InvalidInputException, InvalidDateException {
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
        }
        else if(by.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The deadline of a deadline cannot be empty.");
        }
        else {
            this.by = cleanWhiteSpace(by);
            parseDate(this.by);
        }
    }

    /**
     * Parses the date given by the user from a string format to
     * a LocalDateTime format.
     *
     * @param date the date provided by the user
     * @throws InvalidDateException if the date provided cannot be interpreted by this method
     */
    public void parseDate(String date) throws InvalidDateException {
        String[] brokenDate = date.split("[\\s/-]+");
        try {
            int year = Integer.parseInt(brokenDate[2]);
            int month = Integer.parseInt(brokenDate[1]);
            int day = Integer.parseInt(brokenDate[0]);;
            int hour = Integer.parseInt(brokenDate[3].substring(0, 2));
            int minute = Integer.parseInt(brokenDate[3].substring(2));
            deadline = LocalDateTime.of(year, month, day, hour, minute);
        } catch (Exception e) {
            throw new InvalidDateException("Date provided is not valid");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getMonth() + " " + deadline.getDayOfMonth() + " " +
                deadline.getYear()+ ")";
    }
}
