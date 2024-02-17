package fishstock;

import java.time.LocalDateTime;

/**
 * Encapsulates a Deadline Task.
 * This Task has a description and deadline date.
 */
class Deadline extends Task {
    private static final String BY_KEYWORD = "/by";
    private LocalDateTime by;

    /**
     * Initialize Deadline object manually.
     * @param description The task description.
     * @param by The deadline date.
     */
    protected Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    protected Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    private static void checkIsValid(String[] splitInput) throws FishStockException {
        if (splitInput[0].isEmpty()) {
            throw new FishStockException("OH NOSE! The description of deadline cannot be empty..");
        }
        if (splitInput[1] == null) {
            throw new FishStockException("OH NOSE! \"" + BY_KEYWORD + "\" not found..");
        }
        if (splitInput[1].isEmpty()) {
            throw new FishStockException("OH NOSE! The by-date cannot be empty..");
        }
    }

    /**
     * Initialize Deadline object from input.
     * Has format "deadline [description] /by [date]".
     * @param input The input from user.
     * @return The generated Deadline object.
     * @throws FishStockException The exceptions while creating the Deadline object.
     */
    protected static Deadline of(UserInput input) throws FishStockException {
        assert input.getCommandType() == Command.DEADLINE : "The input type is not Deadline";

        String[] splitInput = input.splitByKeywords(BY_KEYWORD);
        checkIsValid(splitInput);

        String description = splitInput[0];
        LocalDateTime by = Parser.parseDate(splitInput[1]);
        return new Deadline(description, by);
    }

    @Override
    protected Task clone() {
        return new Deadline(this.getDescription(), this.getIsDone(), by);
    }

    @Override
    protected String toSaveString() {
        return Command.DEADLINE.getShortened() + "|" + getDescription() + "|" + Parser.inDate(by)
                + "|" + toSaveIsDone() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + Command.DEADLINE.getShortened() + "]" + super.toString() + " (by: " + Parser.outDate(by) + ")";
    }
}
