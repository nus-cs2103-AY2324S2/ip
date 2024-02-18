package duke.task;

import duke.parser.MissingInputFieldException;

/**
 * Represents a task.
 */
public abstract class Task implements Comparable<Task> {
    protected static String dataStringSplitter = " \\| ";
    protected static String storageDataStringSplitter = " | ";
    protected TaskType type;
    protected String description;
    protected String command;
    protected String delimiter;
    private boolean isDone;

    /**
     * Returns a task of the specified type.
     */
    public Task(TaskType type) {
        this.type = type;
        isDone = false;
    }

    /**
     * Represents three task types: ToDO, Deadline and Event.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
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
        if (type.equals(ToDo.COMMAND)) {
            return new ToDo(input);
        } else if (type.equals(Deadline.COMMAND)) {
            return new Deadline(input);
        } else if (type.equals(Event.COMMAND)) {
            return new Event(input);
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getIsDoneStatus() {
        return isDone ? "X" : "   ";
    }

    /**
     * Gets a string describing update operation status.
     */
    public String getUpdateIsDoneMessage() {
        if (isDone) {
            return "Nice! I've marked this task as done:"
                    + '\n'
                    + "    "
                    + this;
        } else {
            return "OK, I've marked this task as not done yet:"
                    + '\n'
                    + "    "
                    + this;
        }
    }

    /**
     * Parses input into a string array with preset delimiter.
     *
     * @param input String to be parsed.
     */
    public String[] parseInput(String input) {
        return input.split(getDelimiter());
    }

    /**
     * Converts task to storage data format.
     *
     * @return String representing the data in storage format.
     */
    public String convertToDataRow() {
        return getTypeString() + storageDataStringSplitter + boolToInt(isDone) + storageDataStringSplitter
                + description;
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
            Task task;
            String typeString = inputArray[0];
            if (typeString.equals(ToDo.TYPE_STRING)) {
                task = createTask(ToDo.COMMAND, ToDo.COMMAND + " " + inputArray[2]);
            } else if (typeString.equals(Event.TYPE_STRING)) {
                task = createTask(Event.COMMAND, Event.COMMAND + " " + inputArray[2] + " /from "
                        + inputArray[3] + " /to" + inputArray[4]);
            } else if (typeString.equals(Deadline.TYPE_STRING)) {
                task = createTask(Deadline.COMMAND, Deadline.COMMAND + " " + inputArray[2] + " /by "
                        + inputArray[3]);
            } else {
                throw new RuntimeException("Data Corrupted: No Matching duke.task.Task Type");
            }
            if (isTaskDataEntryDone(inputArray)) {
                task.isDone = true;
            }
            return task;
        } catch (MissingInputFieldException | ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Data Corrupted: Missing Input Field");
        }
    }

    private static boolean isTaskStringArray(String[] inputArray) {
        switch (inputArray[0]) {
        case ToDo.TYPE_STRING:
        case Event.TYPE_STRING:
        case Deadline.TYPE_STRING:
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

    @Override
    public int compareTo(Task task) {
        if (type == task.type) {
            return compareToSameType(task);
        }
        return getTypeString().compareTo(task.getTypeString());
    }

    /**
     * Prints type of the task.
     *
     * @return String representing task type.
     */
    public abstract String getTypeString();
    public abstract String getCommand();
    public abstract String getDelimiter();
    public abstract int compareToSameType(Task task);

    /**
     * Sets up task with string input.
     *
     * @param input Input from user specifying task properties.
     * @throws MissingInputFieldException If number of input fields does not match with requirements.
     */
    public abstract void setUpTask(String input) throws MissingInputFieldException;
}