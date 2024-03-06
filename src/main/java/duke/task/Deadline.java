package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.parser.MissingInputFieldException;
import duke.parser.Parser;
import duke.parser.WrongDateTimeInputException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    public static final String DELIMITER = "deadline|/by";
    public static final String COMMAND = "deadline";
    public static final String TYPE_STRING = "D";
    private LocalDateTime deadlineTiming = null;

    /**
     * Returns an instance of Deadline object created using user input.
     *
     * @param input User input to create Deadlien with.
     * @throws MissingInputFieldException When input is missing
     * @throws WrongDateTimeInputException When date&time format is wrong
     */
    public Deadline(String input) throws MissingInputFieldException, WrongDateTimeInputException {
        super(TaskType.DEADLINE);
        setUpTask(input);
    }

    @Override
    public void setUpTask(String input) throws MissingInputFieldException, WrongDateTimeInputException {
        try {
            input = input.trim();
            if (!input.contains(getCommand())) {
                throw new RuntimeException("not deadline");
            }
            String[] inputArray = Task.removeEmptyElements(input.split(getDelimiter()));
            description = inputArray[0].trim();
            deadlineTiming = Parser.parseDateAndTime(inputArray[1].trim());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingInputFieldException(type);
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeInputException();
        }
    }

    @Override
    public String toString() {
        return "[" + getTypeString() + "]" + "[" + getIsDoneStatus() + "] "
                + description + " " + "(by: " + Ui.printTime(deadlineTiming) + ")";
    }

    @Override
    public String convertToDataRow() {
        return super.convertToDataRow() + 0 + storageDataStringSplitter
                + Storage.convertDateTimeForStorage(deadlineTiming);
    }

    @Override
    public String getCommand() {
        return COMMAND;
    }

    @Override
    public String getDelimiter() {
        return DELIMITER;
    }

    @Override
    public String getTypeString() {
        return TYPE_STRING;
    }

    /**
     * Compare tasks of the same type.
     */
    public int compareToSameType(Task task) {
        assert(task.type == TaskType.DEADLINE) : "not a deadline";
        Deadline deadline = (Deadline) task;

        if (!deadlineTiming.equals(deadline.deadlineTiming)) {
            return deadlineTiming.compareTo(deadline.deadlineTiming);
        }

        if (!description.equals(deadline.description)) {
            return description.compareTo(deadline.description);
        }

        return 0;
    }
}