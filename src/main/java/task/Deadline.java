/**
 * This is a Deadline Task.
 * It is a task that contains a deadline.
 */

package task;

import java.time.LocalDate;
public class Deadline extends Task {

    protected String by;
    protected LocalDate deadline;

    public Deadline(String description, String by) throws InvalidInputException, InvalidDateException {
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (by.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The deadline of a deadline cannot be empty.");
        } else {
            this.by = cleanWhiteSpace(by);
            parseDate(this.by);
        }
    }

    public Deadline(String description, String by, boolean storage) throws InvalidInputException, InvalidDateException {
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (by.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The deadline of a deadline cannot be empty.");
        } else {
            this.by = cleanWhiteSpace(by);
            parseStorageDate(this.by);
        }
    }

    /**
     * Parses the date given by the user from a string format to
     * a LocalDateTime format.
     *
     * @param date the date provided by the user
     * @throws InvalidDateException if the date provided cannot be interpreted by this method
     */
    private void parseDate(String date) throws InvalidDateException {
        String[] brokenDate = date.split("[\\s/-]+");
        try {
            int year = Integer.parseInt(brokenDate[0]);
            int month = Integer.parseInt(brokenDate[1]);
            int day = Integer.parseInt(brokenDate[2]);
            deadline = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new InvalidDateException("Date provided is not valid");
        }
    }

    private void parseStorageDate(String date) throws InvalidDateException {
        String[] brokenDate = date.split("[\\s/-]+");
        try {
            int year = Integer.parseInt(brokenDate[2]);
            int month = monthParser(brokenDate[0]);
            int day = Integer.parseInt(brokenDate[1]);
            deadline = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new InvalidDateException("Date provided is not valid");
        }
    }

    private int monthParser(String month) {
        int result = 0;

        switch (month) {
            case "JANUARY":
                result = 1;
                break;
            case "FEBRUARY":
                result = 2;
                break;
            case "MARCH":
                result = 3;
                break;
            case "APRIL":
                result = 4;
                break;
            case "MAY":
                result = 5;
                break;
            case "JUNE":
                result = 6;
                break;
            case "JULY":
                result = 7;
                break;
            case "AUGUST":
                result = 8;
                break;
            case "SEPTEMBER":
                result = 9;
                break;
            case "OCTOBER":
                result = 10;
                break;
            case "NOVEMBER":
                result = 11;
                break;
            case "DECEMBER":
                result = 12;
                break;
        }

        return result;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getMonth() + " " + deadline.getDayOfMonth() + " " +
                deadline.getYear() + ")";
    }
}
