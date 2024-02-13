package fishstock;

import java.time.LocalDateTime;

/**
 * Encapsulates a Deadline Task.
 * This Task has a description and deadline date.
 */
class Deadline extends Task {
    protected static final String COMMAND = "deadline";
    private static final String BY_KEYWORD = " /by ";
    private LocalDateTime by;

    /**
     * Initialize Deadline object manually.
     * @param description The task description.
     * @param by The deadline date.
     */
    protected Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Initialize Deadline object from input.
     * Has format "deadline [description] /by [date]".
     * @param input The input from user.
     * @return The generated Deadline object.
     * @throws FishStockException The exceptions while creating the Deadline object.
     */
    protected static Deadline of(String input) throws FishStockException {
        assert Parser.startsWith(COMMAND, input) : "The input type is not Deadline";

        int byIdx = input.indexOf(BY_KEYWORD);
        if (byIdx == -1) {
            throw new FishStockException("OH NOSE! \"" + BY_KEYWORD + "\" not found..");
        }
        if (COMMAND.length() + 1 > byIdx) {
            throw new FishStockException("OH NOSE! The description of deadline cannot be empty..");
        }
        if (byIdx + BY_KEYWORD.length() == input.length()) {
            throw new FishStockException("OH NOSE! The by-date cannot be empty..");
        }
        String description = input.substring(COMMAND.length() + 1, byIdx);
        String byStr = input.substring(byIdx + BY_KEYWORD.length());
        LocalDateTime by = Parser.parseDate(byStr);
        return new Deadline(description, by);
    }

    @Override
    protected String toSaveString() {
        return "D|" + getDescription() + "|" + Parser.inDate(by) + "|" + toSaveIsDone() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.outDate(by) + ")";
    }
}
