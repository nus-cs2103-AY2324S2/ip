package jelly;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Event task
 */
public class Event extends Task {

    private String start;
    private String end;

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isParsed = false;

    /**
     * @param name   Name of event
     * @param start  start date/time of event
     * @param end    end date/time of event
     * @param isDone whether task is done
     */
    public Event(String name, String start, String end, boolean isDone) {
        super(name, isDone);
        this.start = start;
        this.end = end;

        try {
            this.startDate = LocalDate.parse(start);
            this.endDate = LocalDate.parse(end);
            this.isParsed = true;

        } catch (DateTimeParseException e) {

            System.out.println("Date cannot be parsed, stored as String instead.");
        }
    }

    @Override
    public String toString() {

        if (isParsed) {

            return "[E]" + super.toString() + "(from: " + startDate + " to: " + endDate + ")";
        }
        return "[E]" + super.toString() + "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String header() {

        int binary = super.isDone ? 1 : 0;
        return this.type() + binary;
    }

    @Override
    public String type() {

        return "E";
    }

    @Override
    public String additionalInfo() {

        return "/" + start + "/" + end;
    }
}
