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
        setUpTask(input);
    }

    @Override
    public void setUpTask(String input) throws MissingInputFieldException {
        try {
            input = input.trim();
            if (!input.contains(getCommand())) {
                throw new RuntimeException("not todo");
            }
            String[] inputArray = Task.removeEmptyElements(input.split(getDelimiter()));
            description = inputArray[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingInputFieldException(type);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getTypeString() + "]" + "[" + getIsDoneStatus() + "] " + getDescription();
    }

    @Override
    public String convertToDataRow() {
        return super.convertToDataRow();
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

    public int compareToSameType(Task task) {
        assert(task.type == TaskType.TODO) : "not an todo";
        ToDo todo = (ToDo) task;
        return description.compareTo(todo.description);
    }
}