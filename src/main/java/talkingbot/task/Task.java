package talkingbot.task;

import java.time.format.DateTimeFormatter;

import talkingbot.exception.TalkingBotException;
import talkingbot.type.TaskType;

/**
 * Class (abstract) for tasks.
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
    private static final String EVENT_ERROR_MSG = "ERROR! event descriptions cannot be empty,\n"
            + " and must have"
            + " /from and /to properties.";
    private static final String DO_WITHIN_PERIOD_ERROR_MSG = "ERROR! do_within_period descriptions\n"
            + " cannot be empty and must have /between and /and properties";
    private static final String GENERIC_ERR_MSG = "ERROR! Unknown command format detected!";
    private static final String SAVE_FILE_PROCESSING_ERR_MSG = "ERROR! Invalid line processed!";
    private static final String INVALID_TYPE_ERR_MSG = "ERROR! Invalid type detected.";
    private final String description;
    private boolean isDone;
    private final TaskType taskType;

    /**
     * Class constructor for Task.
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
     * Generates a Task object corresponding to the given
     * description input.
     *
     * @param fullDescription Full description of the task.
     * @param type Type of the task.
     * @return A task: either a Todo, Deadline, or Event.
     * @throws TalkingBotException If fullDescription is empty, or if any time property is missing.
     */
    public static Task generateTask(String fullDescription, String type)
            throws TalkingBotException {
        String[] splitArr = getSplitArr(fullDescription, type);
        switch (type) {
        case "todo":
            if (fullDescription.isEmpty()) {
                throw new TalkingBotException(TODO_ERROR_MSG);
            }
            return new Todo(fullDescription);
        case "deadline":
            if (splitArr.length != 2) {
                throw new TalkingBotException(DEADLINE_ERROR_MSG);
            }
            String deadlineDescription = splitArr[0];
            String deadlineEndTime = splitArr[1];
            return new Deadline(deadlineDescription, deadlineEndTime);
        case "event":
            if (splitArr.length != 3) {
                throw new TalkingBotException(EVENT_ERROR_MSG);
            }
            String eventDescription = splitArr[0];
            String eventBeginTime = splitArr[1];
            String eventEndTime = splitArr[2];
            return new Event(eventDescription, eventBeginTime, eventEndTime);
        case "do_within_period":
            if (splitArr.length != 3) {
                throw new TalkingBotException(DO_WITHIN_PERIOD_ERROR_MSG);
            }
            String doWithinPeriodDescription = splitArr[0];
            String doWithinPeriodBeginTime = splitArr[1];
            String doWithinPeriodEndTime = splitArr[2];
            return new DoWithinPeriod(doWithinPeriodDescription,
                    doWithinPeriodBeginTime, doWithinPeriodEndTime);
        default:
            throw new TalkingBotException(INVALID_TYPE_ERR_MSG);
        }
    }

    /**
     * Generates the String array needed to create each of the task types.
     *
     * @param fullDescription Line to be processed.
     * @param type Type of task.
     * @return A String[] containing information on the task.
     * @throws TalkingBotException If an invalid type is entered.
     */
    private static String[] getSplitArr(String fullDescription, String type) throws TalkingBotException {
        switch (type) {
        case "todo":
            return new String[]{fullDescription};
        case "deadline":
            return fullDescription.split(" /by ");
        case "event":
            return fullDescription.split("( /from )|( /to )");
        case "do_within_period":
            return fullDescription.split("( /between )|( /and )");
        default:
            throw new TalkingBotException(GENERIC_ERR_MSG);
        }
    }

    /**
     * Generates a task from a line in the save file.
     *
     * @param line The current line being processed.
     * @return A task: either a Todo, Deadline, or Event.
     */
    public static Task generateTaskFromFile(String line) throws TalkingBotException {
        String[] lineArr = line.split(" \\| ");
        if (lineArr.length < 2) {
            throw new TalkingBotException(SAVE_FILE_PROCESSING_ERR_MSG);
        }
        String taskType = lineArr[0];
        boolean mark = lineArr[1].equals("1") ? true : false;
        if (taskType.equals("T")) {
            return new Todo(lineArr[2], mark);
        } else if (taskType.equals("D")) {
            return new Deadline(lineArr[2], mark, lineArr[3]);
        } else if (taskType.equals("E")) {
            return new Event(lineArr[2], mark, lineArr[3], lineArr[4]);
        } else if (taskType.equals("W")) {
            return new DoWithinPeriod(lineArr[2], mark, lineArr[3], lineArr[4]);
        } else {
            throw new TalkingBotException(SAVE_FILE_PROCESSING_ERR_MSG);
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
     * Returns the task's representation
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
