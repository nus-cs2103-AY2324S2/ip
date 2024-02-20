package tasks;

import java.time.LocalDate;

import exceptions.ArgumentException;
import parser.Parser;

/**
 * Represents a task in the TaskList.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String status) {
        this.description = description;
        this.isDone = false;
        if (status.equals("Y")) {
            this.isDone = true;
        }
    }

    /**
     * Returns a task generated from the given taskData.
     *
     * @param taskData Array of Strings to be converted to a task. Should be of the format [Type, status, description].
     * @return Corresponding task.
     * @throws ArgumentException if the first element of the array is not a recognised Task type.
     */
    public static Task makeTask(String[] taskData) throws ArgumentException {
        String taskType = taskData[0];
        switch (taskType) {
        case "T":
            return new ToDo(taskData[2], taskData[1]);
        case "D":
            String[] args = Parser.parseDeadlineArgument(taskData[2]);
            return new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()), taskData[1]);
        case "E":
            args = Parser.parseEventArgument(taskData[2]);
            return new Event(args[0].trim(), LocalDate.parse(args[1].trim()),
                    LocalDate.parse(args[2].trim()), taskData[1]);
        default:
            throw new ArgumentException("Did not recognize task type " + taskType + "\n");
        }
    }

    /**
     * Returns a task generated from the given taskType and taskArgument.
     *
     * @param taskType String indicting the type of task.
     * @param taskArgument String containing information needed to create the task.
     * @return Corresponding Task.
     * @throws ArgumentException if too little arguments are provided or taskType is not recognised.
     */
    public static Task makeTask(String taskType, String taskArgument) throws ArgumentException {
        switch (taskType) {
        case "todo":
            if (taskArgument.length() == 0) {
                throw new ArgumentException("Insufficient argument provided for todo task.\n");
            }
            return new ToDo(taskArgument);
        case "deadline":
            String[] args = Parser.parseDeadlineArgument(taskArgument);
            return new Deadline(args[0].trim(), LocalDate.parse(args[1].trim()));
        case "event":
            args = Parser.parseEventArgument(taskArgument);
            return new Event(args[0].trim(), LocalDate.parse(args[1].trim()),
                    LocalDate.parse(args[2].trim()));
        default:
            throw new ArgumentException("Did not recognize task type " + taskType + "\n");
        }
    }

    /**
     * Returns "X" if task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Updates the status of this task to be done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Updates the status of this task to be not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    public boolean isFound(String matcher) {
        return Parser.matchStrings(this.description, matcher);
    }

    /**
     * Returns the save format of this Task containing its status and description.
     */
    public String toSaveFormat() {
        if (this.isDone) {
            return "Y " + this.description;
        } else {
            return "N " + this.description;
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
