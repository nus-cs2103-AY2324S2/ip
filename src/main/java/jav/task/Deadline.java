package jav.task;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;

/**
* Deadline is a task that also consists of a due date.
*/
public class Deadline extends Task {
    /** Non-date formatted due date. */
    protected String dueText;

    /** Date formatted due date. */
    protected LocalDate dueDate;

    /**
     * Constructs a new Deadline.
     *
     * @return a new Deadline.
     */
    public Deadline() {
        type = StorageManager.StorageType.DEADLINE;
        description = "deadline";
        dueDate = LocalDate.now();
        isMarked = false;
    }

    /**
     * Constructs a new Deadline.
     *
     * @param params a string containing the information about the deadline.
     * @param isMarked whether the deadline is marked.
     * @return a new Deadline.
     * @throws InvalidParamException if the parameters are invalid.
     */
    public Deadline(String params, boolean isMarked) throws InvalidParamException {
        // Check for invalid params
        if (!params.contains(" /by ")) {
            throw new InvalidParamException("Invalid param for deadline", null);
        }

        // Set the deadline values
        type = StorageManager.StorageType.DEADLINE;
        description = params.substring(0, params.indexOf(" /by "));
        dueText = params.substring(params.indexOf(" /by ") + 5);
        try {
            dueDate = LocalDate.parse(dueText);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        String s = "[D]"
                 + super.toString()
                 + String.format(" (by: %s)", dueDate != null ? dueDate.toString() : dueText);

        if (dueDate != null) {
            Period p = Period.between(LocalDate.now(), dueDate);
            int i = p.getDays();
            if (p.getMonths() >= 1) {
                s += " {a month or more left}";
            } else if (i >= 2) {
                s += " {" + i + " days left}";
            } else if (i == 1) {
                s += " {" + i + " day left}";
            } else if (i == 0) {
                s += " {TODAY}";
            } else if (i < 0) {
                s += " {ALREADY OVER}";
            } else {
                assert false : "Impossible condition";
            }
        }

        return s;
    }

    @Override
    public String getFileFormatParam() {
        return description + " /by " + dueText;
    }
}
