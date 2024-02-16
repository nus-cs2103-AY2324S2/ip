package remi.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * General task class. Has a label and a flag denoting whether the task is done or not.
 */

public class Task {
    private String label;
    private boolean isDone;

    /**
     * Tries to parse a string as a date, if impossible returns the original string.
     * Parses from "yyyy-MM-dd" and returns "dd MMM yyyy".
     *
     * @param input input string that may or may not be a date
     * @return the date as a string in "dd MMM yyyy" format
     */
    protected String formatDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } catch (DateTimeParseException e) {
            return input;
        }
    }

    public String getLabel() {
        return label;
    }
    public Task(String label) {
        this.label = label;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the queryString is located in the label parameter of this object.
     *
     * @param queryString the string we are querying
     */
    public boolean checkQuery(String queryString) {
        return this.label.contains(queryString);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task and all its details
     */
    @Override
    public String toString() {
        String res = "";
        if (this.isDone) {
            res += "[X]";
        } else {
            res += "[ ]";
        }

        return res + " " + label;
    }

    /**
     * Returns a parsable string of the task. Meant to be used for remi.storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    public String getParsableString() {
        String res = "";
        if (this.isDone) {
            res += "X";
        } else {
            res += "V";
        }

        return res + "|" + label;
    }
}
