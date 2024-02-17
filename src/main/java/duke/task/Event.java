package duke.task;

import java.time.LocalDateTime;

import duke.parser.MissingInputFieldException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Represents an event.
 */
public class Event extends Task {

    public static final String TYPE_STRING = "E";
    public static final String DELIMITER = "event|/from|/to";
    public static final String COMMAND = "event";
    private LocalDateTime from = null;
    private LocalDateTime to = null;

    /**
     * Returns an instance of event created with user input.
     *
     * @param input User specified input to create Event.
     * @throws MissingInputFieldException
     */
    public Event(String input) throws MissingInputFieldException {
        super(TaskType.EVENT);
        delimiter = DELIMITER;
        command = COMMAND;
        setUpTask(input);
    }

    @Override
    public String getType() {
        return TYPE_STRING;
    }

    @Override
    public void setUpTask(String input) throws MissingInputFieldException {
        try {
            input = input.trim();
            if (!input.contains(command)) {
                throw new RuntimeException("not todo");
            }
            String[] inputArray = Task.removeEmptyElements(input.split(delimiter));
            description = inputArray[0].trim();
            from = Parser.parseDateAndTime(inputArray[1].trim());
            to = Parser.parseDateAndTime(inputArray[2].trim());
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingInputFieldException(type);
        }
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "["
                + getIsDoneStatus() + "] "
                + description + " " + "(from: "
                + Ui.printTime(from) + " to: " + Ui.printTime(to) + ")";
    }

    @Override
    public String convertToDataRow() {
        return super.convertToDataRow() + storageDataStringSplitter + Storage.convertDateTimeForStorage(from)
                + storageDataStringSplitter + Storage.convertDateTimeForStorage(to);
    }
}