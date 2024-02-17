package duke.task;

import duke.parser.MissingInputFieldException;

/**
 * Represents ToDos.
 */
public class ToDo extends Task {
    public static final String TYPE_STRING = "T";
    public static final String DELIMITER = "todo";
    public static final String COMMAND = "todo";

    /**
     * Creates a ToDo instance with user input
     *
     * @param input User input to create the ToDo with.
     * @throws MissingInputFieldException
     */
    public ToDo(String input) throws MissingInputFieldException {
        super(TaskType.TODO);
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputFieldException(type);
        }
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getIsDoneStatus() + "] " + getDescription();
    }

    @Override
    public String convertToDataRow() {
        return super.convertToDataRow();
    }
}