package aaron.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aaron.exception.AaronBotException;
import aaron.exception.DateFormatException;
import aaron.exception.DateMismatchException;

/**
 * class that represents an event type task
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String taskString, String startTime, String endTime) throws AaronBotException {
        super(taskString);
        try {
            this.startTime = LocalDate.parse(startTime, DATE_TIME_FORMATTER);
            this.endTime = LocalDate.parse(endTime, DATE_TIME_FORMATTER);
            if (this.startTime.isAfter(this.endTime)) {
                throw new DateMismatchException("Mismatched date: " + startTime + " " +
                        endTime);
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatException(startTime + " " +
                    endTime);
        }
    }

    public Event(String taskString, String startTime, String endTime, boolean isDone) throws AaronBotException {
        super(taskString, isDone);
        try {
            this.startTime = LocalDate.parse(startTime, DATE_TIME_FORMATTER);
            this.endTime = LocalDate.parse(endTime, DATE_TIME_FORMATTER);
            if (this.startTime.isAfter(this.endTime)) {
                throw new DateMismatchException("Mismatched date: " + startTime + " " +
                        endTime);
            }
        } catch (DateTimeParseException e) {
            throw new DateFormatException(startTime + " " +
                    endTime);
        }
    }

    @Override
    public void postpone(int postponeAmount) throws AaronBotException {
        startTime = startTime.plusDays(postponeAmount);
        endTime = endTime.plusDays(postponeAmount);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " | " + startTime.format(DATE_TIME_FORMATTER)
                + " | " + endTime.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event event = (Event) obj;
        return (super.equals(event) && this.startTime.equals(event.startTime)
                && this.endTime.equals(event.endTime));
    }

}
