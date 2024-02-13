package duke.task;

import java.time.LocalDate;

import duke.dukeexception.DukeCannotBeMarked;
import duke.dukeexception.DukeCannotBeUnmarked;

/**
 * Represents a Task object added by the user. A <code>Task</code> object contains a String corresponding to the name of
 * the task and a boolean to signify if the task is completed.
 */
public class Task {
    /** Name of the task */
    protected String name;
    /** Status of the task */
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified name and mark whether it is done.
     * @param name The name of the task
     * @param isDone The status of the task
     */
    public Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Returns a String representation of the task used for saving the task. The String will contain information such
     * as the name of the task and the status.
     * @return the string to be saved
     */
    public String saveOutput() {
        return String.format("| %d | %s", isDone ? 1 : 0, name);
    }

    /**
     * Returns a String representation of the task if task is occurring on the date given.
     * @param date the date to check if task was active
     * @return the string representation of task if date is within task timeframe, or an empty string if it is not
     */

    public String happenOn(LocalDate date) {
        return "";
    }

    /**
     * Returns a String representation of the task if task name contains string.
     * @param match the date to check if task was active
     * @return the string representation of task if string is within task name, or an empty string if it is not
     */
    public String printMatch(String match) {
        return "";
    }

    /**
     * Returns true if the name contains the string given, false otherwise
     * @param match string to be checked
     * @return true if name contains string, false otherwise
     */
    public boolean contains(String match) {
        return name.contains(match);
    }

    /**
     * Returns a String representation to be printed out to display for the user. The String will contain information
     * such as the name of the task and status.
     * @return the string representing the task to be printed
     */

    public String taskInfo() {
        String output = "";
        if (isDone) {
            output += "[X]";
        } else {
            output += "[ ]";
        }
        return output + " " + name;
    }

    /**
     * Returns a string to be printed to inform the user that the task has been marked. Updates the isDone variable in
     * the Task object to true as well.
     * @return the string informing user that task has been marked to be printed
     * @throws DukeCannotBeMarked if isDone is false
     */

    public String mark() throws DukeCannotBeMarked {
        if (isDone) {
            throw new DukeCannotBeMarked();
        }

        isDone = true;
        return "Nice! I've marked this task as done:" + this.taskInfo();
    }
    /**
     * Returns a string to be printed to inform the user that the task has been unmarked. Updates the isDone variable in
     * the Task object to false as well.
     * @return the string informing user that task has been unmarked to be printed
     * @throws DukeCannotBeUnmarked if isDone is false
     */
    public String unmark() throws DukeCannotBeUnmarked {
        if (!isDone) {
            throw new DukeCannotBeUnmarked();
        }

        isDone = false;
        return "OK, I've marked this task as not done yet:" + this.taskInfo();
    }
}
