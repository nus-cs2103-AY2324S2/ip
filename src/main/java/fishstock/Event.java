package fishstock;

import java.time.LocalDateTime;

/**
 * Encapsulates an Event Task.
 * This Task has a description, from date, and to date.
 */
class Event extends Task {
    protected static final String COMMAND = "event";
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";
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

    /**
     * Initialize Event object from input.
     * Has format "event [description] /from [date] /to [date]".
     * @param input The input from user.
     * @return The generated Event object.
     * @throws FishStockException The exceptions while creating the Event object.
     */
    protected static Event of(String input) throws FishStockException {
        assert Parser.startsWith(COMMAND, input) : "The input type is not Event";

        int fromIdx = input.indexOf(FROM_KEYWORD);
        if (fromIdx == -1) {
            throw new FishStockException("OH NOSE! \"" + FROM_KEYWORD + "\" not found..");
        }
        if (COMMAND.length() + 1 > fromIdx) {
            throw new FishStockException("OH NOSE! The description of event cannot be empty..");
        }
        int toIdx = input.indexOf(TO_KEYWORD);
        if (toIdx == -1) {
            throw new FishStockException("OH NOSE! \"" + TO_KEYWORD + "\" not found..");
        }
        if (fromIdx > toIdx) {
            throw new FishStockException("OH NOSE! The from-date must be put first..");
        }
        if (fromIdx + FROM_KEYWORD.length() > toIdx) {
            throw new FishStockException("OH NOSE! The from-date cannot be empty..");
        }
        if (toIdx + TO_KEYWORD.length() == input.length()) {
            throw new FishStockException("OH NOSE! The to-date cannot be empty..");
        }

        String description = input.substring(COMMAND.length() + 1, fromIdx);
        String fromStr = input.substring(fromIdx + FROM_KEYWORD.length(), toIdx);
        String toStr = input.substring(toIdx + TO_KEYWORD.length());
        LocalDateTime from = Parser.parseDate(fromStr);
        LocalDateTime to = Parser.parseDate(toStr);
        return new Event(description, from, to);
    }

    @Override
    protected String toSaveString() {
        return "E|" + getDescription() + "|" + Parser.inDate(from) + "|" + Parser.inDate(to) + "|"
                + toSaveIsDone() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.outDate(from) + " to: " + Parser.outDate(to) + ")";
    }
}
