package talkingbot.task;

import talkingbot.type.TaskType;

/**
 * A class that represents the Todo task.
 */
public class Todo extends Task {
    private static final String TODO_SAVE_FILE_FORMAT = "T | %d | %s";
    private static final String TODO_DISPLAY_FORMAT = "[T] %s";
    /**
     * Constructor for the Todo class.
     *
     * @param description Description of the task.
     * @param isDone Marks whether the task has been done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Returns the String describing this task that will be saved into
     * the save file.
     *
     * @return A formatted String.
     */
    @Override
    public String getSaveFileString() {
        return String.format(TODO_SAVE_FILE_FORMAT, super.getDoneAsInt(),
                super.getDescription());
    }

    /**
     * Returns how the Todo object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return String.format(TODO_DISPLAY_FORMAT, super.toString());
    }
}
