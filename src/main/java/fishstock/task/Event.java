package fishstock.task;

import java.time.LocalDateTime;

import fishstock.Command;
import fishstock.UserInput;

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
     * Initializes an Event object.
     *
     * @param description The task description.
     * @param from The from date.
     * @param to The to date.
     */
    protected Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    protected Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    private static void checkIsValid(String[] splitInput) throws TaskException {
        if (splitInput[0].isEmpty()) {
            throw new TaskException("OH NOSE! The description of event cannot be empty..");
        }
        if (splitInput[1] == null) {
            throw new TaskException("OH NOSE! \"" + FROM_KEYWORD + "\" not found..");
        }
        if (splitInput[0].contains(TO_KEYWORD)) {
            throw new TaskException("OH NOSE! The from-date must be put first..");
        }
        if (splitInput[2] == null) {
            throw new TaskException("OH NOSE! \"" + TO_KEYWORD + "\" not found..");
        }
        if (splitInput[1].isEmpty()) {
            throw new TaskException("OH NOSE! The from-date cannot be empty..");
        }
        if (splitInput[2].isEmpty()) {
            throw new TaskException("OH NOSE! The to-date cannot be empty..");
        }

        LocalDateTime from = DateParser.parseDate(splitInput[1]);
        LocalDateTime to = DateParser.parseDate(splitInput[2]);

        if (from.isAfter(to) && !from.equals(to)) {
            throw new TaskException("OH NOSE! The from-date must be before the to-date..");
        }
    }

    /**
     * Initializes an Event object from UserInput.
     * Has format "event [description] /from [date] /to [date]".
     *
     * @param input The input from user.
     * @return The generated Event object.
     * @throws TaskException The exceptions while creating the Event object.
     */
    protected static Event of(UserInput input) throws TaskException {
        assert input.getCommandType() == Command.EVENT : "The input type is not Event";

        String[] splitInput = input.splitByKeywords(FROM_KEYWORD, TO_KEYWORD);
        checkIsValid(splitInput);

        String description = splitInput[0];
        LocalDateTime from = DateParser.parseDate(splitInput[1]);
        LocalDateTime to = DateParser.parseDate(splitInput[2]);
        return new Event(description, from, to);
    }

    @Override
    public Task clone() {
        return new Event(this.getDescription(), this.getIsDone(), from, to);
    }

    @Override
    public String toSaveFormat() {
        return Command.EVENT.getShortened() + "|" + getDescription() + "|" + DateParser.inDate(from) + "|"
                + DateParser.inDate(to) + "|" + markStatusToInt() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + Command.EVENT.getShortened() + "]" + super.toString() + " (from: " + DateParser.outDate(from)
                + " to: " + DateParser.outDate(to) + ")";
    }
}
