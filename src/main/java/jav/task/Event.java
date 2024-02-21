package jav.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;

/**
* Event is a task that also consists of a start and end date.
*/
public class Event extends Task {
    /** Non-date formatted start date. */
    protected String startText;

    /** Non-date formatted end date. */
    protected String endText;

    /** Date formatted start date. */
    protected LocalDate startDate;

    /** Date formatted end date. */
    protected LocalDate endDate;

    /**
     * Constructs a new Event.
     *
     * @return a new Event.
     */
    public Event() {
        type = StorageManager.StorageType.EVENT;
        description = "event";
        startText = "(NOW)";
        endText = "(NOW)";
        startDate = LocalDate.now();
        endDate = LocalDate.now();
        isMarked = false;

    }

    /**
     * Constructs a new Event.
     *
     * @param params a string containing the information about the event.
     * @param isMarked whether the event is marked.
     * @return a new Event.
     * @throws InvalidParamException if the parameters are invalid.
     */
    public Event(String params, boolean isMarked) throws InvalidParamException {
        // Check for invalid params
        if (!params.contains(" /from ") || (!params.contains(" /to "))) {
            throw new InvalidParamException("Invalid param for event", null);
        }

        // Getting the tokens
        int start = params.indexOf(" /from ");
        int end = params.indexOf(" /to ");
        if (end < start) {
            throw new InvalidParamException("Invalid param for event", null);
        }
        type = StorageManager.StorageType.EVENT;
        description = params.substring(0, start);
        String n = params.substring(start + 7);

        // Set the event values
        startText = n.substring(0, n.indexOf(" /to "));
        endText = n.substring(n.indexOf(" /to ") + 5);
        try {
            startDate = LocalDate.parse(startText);
            endDate = LocalDate.parse(endText);
        } catch (DateTimeParseException e) {
            startDate = null;
            endDate = null;
        }

        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        String s = "[E]"
                 + super.toString()
                 + String.format(" (from: %s to: %s)",
                                startDate != null ? startDate.toString() : startText,
                                endDate != null ? endDate.toString() : endText);
        return s;
    }

    @Override
    public String getFileFormatParam() {
        return description + " /from " + startText + " /to " + endText;
    }
}
