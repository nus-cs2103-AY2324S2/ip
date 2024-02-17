package fishstock;

import java.time.LocalDateTime;

/**
 * Encapsulates an Event Task.
 * This Task has a description, from date, and to date.
 */
class Event extends Task {
    private static final String FROM_KEYWORD = "/from";
    private static final String TO_KEYWORD = "/to";
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initialize Event object manually.
     * @param description The task description.
     * @param from The from date.
     * @param to The to date.
     */
    protected Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    protected Event(String description, LocalDateTime from, LocalDateTime to) throws FishStockException {
        this(description, false, from, to);
    }

    private static void checkIsValid(String[] splitInput) throws FishStockException {
        if (splitInput[0].isEmpty()) {
            throw new FishStockException("OH NOSE! The description of event cannot be empty..");
        }
        if (splitInput[1] == null) {
            throw new FishStockException("OH NOSE! \"" + FROM_KEYWORD + "\" not found..");
        }
        if (splitInput[0].contains(TO_KEYWORD)) {
            throw new FishStockException("OH NOSE! The from-date must be put first..");
        }
        if (splitInput[2] == null) {
            throw new FishStockException("OH NOSE! \"" + TO_KEYWORD + "\" not found..");
        }
        if (splitInput[1].isEmpty()) {
            throw new FishStockException("OH NOSE! The from-date cannot be empty..");
        }
        if (splitInput[2].isEmpty()) {
            throw new FishStockException("OH NOSE! The to-date cannot be empty..");
        }

        LocalDateTime from = Parser.parseDate(splitInput[1]);
        LocalDateTime to = Parser.parseDate(splitInput[2]);

        if (from.isAfter(to) && !from.equals(to)) {
            throw new FishStockException("OH NOSE! The from-date must be before the to-date..");
        }
    }

    /**
     * Initialize Event object from input.
     * Has format "event [description] /from [date] /to [date]".
     * @param input The input from user.
     * @return The generated Event object.
     * @throws FishStockException The exceptions while creating the Event object.
     */
    protected static Event of(UserInput input) throws FishStockException {
        assert input.getCommandType() == Command.EVENT : "The input type is not Event";

        String[] splitInput = input.splitByKeywords(FROM_KEYWORD, TO_KEYWORD);
        checkIsValid(splitInput);

        String description = splitInput[0];
        LocalDateTime from = Parser.parseDate(splitInput[1]);
        LocalDateTime to = Parser.parseDate(splitInput[2]);
        return new Event(description, from, to);
    }

    @Override
    protected Task clone() {
        return new Event(this.getDescription(), this.getIsDone(), from, to);
    }

    @Override
    protected String toSaveString() {
        return Command.EVENT.getShortened() + "|" + getDescription() + "|" + Parser.inDate(from) + "|"
                + Parser.inDate(to) + "|" + toSaveIsDone() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + Command.EVENT.getShortened() + "]" + super.toString() + " (from: " + Parser.outDate(from)
                + " to: " + Parser.outDate(to) + ")";
    }
}
