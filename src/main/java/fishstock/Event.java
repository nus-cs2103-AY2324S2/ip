package fishstock;

import java.io.FileDescriptor;
import java.time.LocalDateTime;

/**
 * Encapsulates an Event Task.
 * This Task has a description, from date, and to date.
 */
class Event extends Task {
    protected final static String keyword = "event";
    private final static String fromKeyword = " /from ";
    private final static String toKeyword = " /to ";
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initialize Event object manually.
     * @param description The task description.
     * @param from The from date.
     * @param to The to date.
     * @throws FishStockException The exceptions while creating the Event object.
     */
    protected Event(String description, LocalDateTime from, LocalDateTime to) throws FishStockException {
        super(description);
        if (from.isAfter(to) && !from.equals(to)) {
            throw new FishStockException("OH NOSE! The from-date must be before the to-date..");
        }
        this.from = from;
        this.to = to;
    }

    protected static boolean dateIsBefore(LocalDateTime from, LocalDateTime to) {
        return from.isBefore(to) || from.equals(to);
    }

    /**
     * Initialize Event object from input.
     * Has format "event [description] /from [date] /to [date]".
     * @param input The input from user.
     * @return The generated Event object.
     * @throws FishStockException The exceptions while creating the Event object.
     */
    protected static Event of(String input) throws FishStockException {
        if (!Parser.startsWith(keyword, input)) {
            throw new FishStockException("OH NOSE! This input is not event..");
        }
        int fromIdx = input.indexOf(fromKeyword);
        if (fromIdx == -1) {
            throw new FishStockException("OH NOSE! \"" + fromKeyword + "\" not found..");
        }
        if (keyword.length() + 1 > fromIdx) {
            throw new FishStockException("OH NOSE! The description of event cannot be empty..");
        }
        int toIdx = input.indexOf(toKeyword);
        if (toIdx == -1) {
            throw new FishStockException("OH NOSE! \"" + toKeyword + "\" not found..");
        }
        if (fromIdx > toIdx) {
            throw new FishStockException("OH NOSE! The from-date must be put first..");
        }
        if (fromIdx + fromKeyword.length() > toIdx) {
            throw new FishStockException("OH NOSE! The from-date cannot be empty..");
        }
        if (toIdx + toKeyword.length() == input.length()) {
            throw new FishStockException("OH NOSE! The to-date cannot be empty..");
        }

        String description = input.substring(keyword.length() + 1, fromIdx);
        String fromStr = input.substring(fromIdx + fromKeyword.length(), toIdx);
        String toStr = input.substring(toIdx + toKeyword.length());
        LocalDateTime from = Parser.parseDate(fromStr);
        LocalDateTime to = Parser.parseDate(toStr);
        return new Event(description, from, to);
    }

    @Override
    protected String toSaveString() {
        return "E|" + description + "|" + Parser.inDate(from) + "|" + Parser.inDate(to) + "|" +
                boolToInt(isDone) + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.outDate(from) + " to: " + Parser.outDate(to) + ")";
    }
}