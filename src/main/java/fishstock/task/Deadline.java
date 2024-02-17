package fishstock.task;

import java.time.LocalDateTime;

import fishstock.Command;
import fishstock.UserInput;

/**
 * Encapsulates a Deadline Task.
 * This Task has a description and deadline date.
 */
class Deadline extends Task {
    private static final String BY_KEYWORD = "/by";
    private LocalDateTime by;

    /**
     * Initializes a Deadline object.
     *
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

    private static void checkIsValid(String[] splitInput) throws TaskException {
        if (splitInput[0].isEmpty()) {
            throw new TaskException("OH NOSE! The description of deadline cannot be empty..");
        }
        if (splitInput[1] == null) {
            throw new TaskException("OH NOSE! \"" + BY_KEYWORD + "\" not found..");
        }
        if (splitInput[1].isEmpty()) {
            throw new TaskException("OH NOSE! The by-date cannot be empty..");
        }
    }

    /**
     * Initializes a Deadline object from UserInput.
     * Has format "deadline [description] /by [date]".
     *
     * @param input The input from user.
     * @return The generated Deadline object.
     * @throws TaskException The exceptions while creating the Deadline object.
     */
    protected static Deadline of(UserInput input) throws TaskException {
        assert input.getCommandType() == Command.DEADLINE : "The input type is not Deadline";

        String[] splitInput = input.splitByKeywords(BY_KEYWORD);
        checkIsValid(splitInput);

        String description = splitInput[0];
        LocalDateTime by = DateParser.parseDate(splitInput[1]);
        return new Deadline(description, by);
    }

    @Override
    public Task clone() {
        return new Deadline(this.getDescription(), this.getIsDone(), by);
    }

    @Override
    public String toSaveFormat() {
        return Command.DEADLINE.getShortened() + "|" + getDescription() + "|" + DateParser.inDate(by)
                + "|" + markStatusToInt() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + Command.DEADLINE.getShortened() + "]" + super.toString() + " (by: " + DateParser.outDate(by) + ")";
    }
}
