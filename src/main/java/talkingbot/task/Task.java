package talkingbot.task;

import java.time.format.DateTimeFormatter;

import talkingbot.exception.TalkingBotException;
import talkingbot.type.TaskType;

/**
 * An abstract class for tasks.
 */
public abstract class Task {
    /** Formatting of datetime used during entry and saving */
    protected static final DateTimeFormatter DATE_TIME_ENTRY_FORMAT = DateTimeFormatter
            .ofPattern("yyyy-MM-dd kkmm");
    /** Formatting of datetime outputs used for displaying to the user */
    protected static final DateTimeFormatter DATE_TIME_OUT_FORMAT = DateTimeFormatter
            .ofPattern("MMM dd yyyy kkmm");
    private static final String TODO_ERROR_MSG = "ERROR! todo descriptions cannot be empty"
            + " nor only containing whitespaces.";
    private static final String DEADLINE_ERROR_MSG = "ERROR! deadline descriptions cannot be empty and must have a /by"
            + " property.";
    private static final String EVENT_ERROR_MSG = "ERROR! event descriptions cannot be empty, and must have"
            + " /from and /to properties.";
    private final String description;
    private boolean isDone;
    private final TaskType taskType;

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     * @param isDone Marks whether the task has been done.
     * @param taskType Type of the task.
     */
    public Task(String description, boolean isDone, TaskType taskType) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Factory method that generates the task object
     * corresponding to the given type.
     *
     * @param fullDescription Full description of the task.
     * @param type Type of the task.
     * @return A task: either a Todo, Deadline, or Event.
     * @throws TalkingBotException If fullDescription is empty, or if any time property is missing.
     */
    public static Task generateTask(String fullDescription, String type)
            throws TalkingBotException {
        if (type.equals("todo")) {
            if (fullDescription.isEmpty()) {
                throw new TalkingBotException(TODO_ERROR_MSG);
            }
            return new Todo(fullDescription, false);
        } else if (type.equals("deadline")) {
            String[] splitArr = fullDescription.split(" /by ");
            try {
                return new Deadline(splitArr[0], false, splitArr[1]);
            } catch (IndexOutOfBoundsException err) {
                throw new TalkingBotException(DEADLINE_ERROR_MSG);
            }
        } else {
            String[] splitArr = fullDescription.split("( /from )|( /to )");
            try {
                return new Event(splitArr[0], false, splitArr[1], splitArr[2]);
            } catch (IndexOutOfBoundsException err) {
                throw new TalkingBotException(EVENT_ERROR_MSG);
            }
        }
    }

    /**
     * Generates a task from a line in the save file.
     *
     * @param line The current line being processed.
     * @return A task: either a Todo, Deadline, or Event.
     */
    public static Task generateTaskFromFile(String line) {
        String[] lineArr = line.split(" \\| ");
        boolean mark = lineArr[1].equals("1") ? true : false;
        if (lineArr[0].equals("T")) {
            return new Todo(lineArr[2], mark);
        } else if (lineArr[0].equals("D")) {
            return new Deadline(lineArr[2], mark, lineArr[3]);
        } else {
            return new Event(lineArr[2], mark, lineArr[3], lineArr[4]);
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether a task is done.
     *
     * @return Whether a task is done.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Returns whether a task is done as an integer.
     * 1 is for tasks done and 0 for tasks undone.
     *
     * @return Whether a task is done as an integer.
     */
    public int getDoneAsInt() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Sets the isDone property depending on the given parameter.
     *
     * @param isDone Value to set the isDone property to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Abstract method to return the task's representation
     * in the save file.
     *
     * @return String that represents the task during saving.
     */
    public abstract String getSaveFileString();

    /**
     * Returns how the Task object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        String addMark = this.getDone() ? "X" : " ";
        return String.format("[%s] %s", addMark, this.getDescription());
    }
}
