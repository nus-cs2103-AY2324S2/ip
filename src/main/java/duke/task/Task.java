package duke.task;

import duke.parser.MissingInputFieldException;

/**
 * Represents a task.
 */
public abstract class Task {
    protected static String dataStringSplitter = " \\| ";
    // temporary measure before storage related methods are migrated over to the storage class
    protected static String storageDataStringSplitter = " | ";
    private static String delimiter;
    private static String command;
    protected TaskType type;
    protected String description;
    private boolean isDone;


    /**
     * Returns a task of the specified type.
     */
    public Task(TaskType type) {
        this.type = type;
        isDone = false;
    }

    public static String getDelimiter() {
        return delimiter;
    }

    public static void setDelimiter(String delimiter) {
        Task.delimiter = delimiter;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        Task.command = command;
    }

    /**
     * Represents three task types: ToDO, Deadline and Event.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNRECOGNIZED
    }

    /**
     * Remove empty elements from the input String array.
     *
     * @param words String array with empty elements to be removed.
     * @return A new copy of the input array, with empty elements removed.
     */
    public static String[] removeEmptyElements(String[] words) {
        String[] result = new String[words.length];
        int index = 0;
        for (String s : words) {
            if (!s.isEmpty()) {
                result[index] = s;
                index++;
            }
        }
        return result;
    }

    // should avoid type string parameter
    /**
     * Creates a task.
     *
     * @param type Type of the task to be created: "todo", "deadline" or "event".
     * @param input String specifying properties of the task.
     * @return Task created.
     * @throws MissingInputFieldException If number of input field does not match with requirement.
     */
    public static Task createTask(String type, String input) throws MissingInputFieldException {
        if (type.equals("todo")) {
            return new ToDo(input);
        } else if (type.equals("deadline")) {
            return new Deadline(input);
        } else if (type.equals("event")) {
            return new Event(input);
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        // obsolete toggles
        this.isDone = isDone;
    }

    protected char getIsDoneStatus() {
        return isDone ? 'X' : ' ';
    }

    /**
     * Gets a string describing update operation status.
     */
    public String getUpdateIsDoneMessage() {
        if (isDone) {
            return "Nice! I've marked this task as done:"
                    + '\n'
                    + "    "
                    + toString();
        } else {
            return "OK, I've marked this task as not done yet:"
                    + '\n'
                    + "    "
                    + toString();
        }
    }

    /**
     * Parses input into a string array with preset delimiter.
     *
     * @param input String to be parsed.
     */
    public String[] parseInput(String input) {
        return input.split(delimiter);
    }

    /**
     * Converts task to storage data format.
     *
     * @return String representing the data in storage format.
     */
    public String convertToDataRow() {
        return getType() + storageDataStringSplitter + boolToInt(isDone) + storageDataStringSplitter + description;
    }

    /**
     * Converts one row of data in storage format to a task.
     *
     * @param dataRow One row of data in storage format.
     * @return Task converted from storage data.
     */
    public static Task convertDataToTask(String dataRow) {
        String[] inputArray = removeEmptyElements(Task.removeEmptyElements(
                dataRow.split(dataStringSplitter)));
        if (!isTaskStringArray(inputArray)) {
            throw new RuntimeException("Data Corrupted: No Matching duke.task.Task Type");
        }
        try {
            Task temp = null;
            if (inputArray[0].equals("T")) {
                temp = createTask("todo", "todo " + inputArray[2]);
            } else if (inputArray[0].equals("E")) {
                temp = createTask("event", "event " + inputArray[2] + " /from "
                        + inputArray[3] + " /to" + inputArray[4]);
            } else if (inputArray[0].equals("D")) {
                temp = createTask("deadline", "deadline " + inputArray[2] + " /by " + inputArray[3]);
            } else {
                throw new RuntimeException("Data Corrupted: No Matching duke.task.Task Type");
            }
            if (isTaskDataEntryDone(inputArray)) {
                temp.isDone = true;
            }
            return temp;
        } catch (MissingInputFieldException | ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Data Corrupted: Missing Input Field");
        }
    }

    public boolean descriptionContainKeyword(String keyword) {
        return description.contains(keyword);
    }

    private static boolean isTaskStringArray(String[] inputArray) {
        switch (inputArray[0]) {
        case "T":
        case "E":
        case "D":
            return true;
        default:
            return false;
        }
    }

    private static boolean isTaskDataEntryDone(String[] inputArray) {
        return inputArray[1].equals("1");
    }

    private static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    /**
     * Prints type of the task.
     *
     * @return String representing task type.
     */
    public abstract String getType();

    /**
     * Sets up task with string input.
     *
     * @param input Input from user specifying task properties.
     * @throws MissingInputFieldException If number of input fields does not match with requirements.
     */
    public abstract void setUpTask(String input) throws MissingInputFieldException;
}