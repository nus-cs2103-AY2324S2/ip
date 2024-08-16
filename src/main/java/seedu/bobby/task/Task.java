package seedu.bobby.task;

import seedu.bobby.BobbyException;

/**
 * <h1> Task </h1>
 * This Task class is a superclass of the Deadlines and Events classes. This class represents the individual Task objects
 * and has attributes description and isDone which represents the description of the task
 * and whether the task is completed respectively. This class handles all the functionalities of a Task object,
 * such as the marking, unmarking of the task and printing the task description to be displayed to the user.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        isDone = false;
    }

    /**
     * Marks the attribute isDone of the Task and returns the string of the task description
     * and the updated marking
     * @param isNew boolean to indicate whether it is a new task marked or it is loading the
     *              tasks from the data file
     * @return String of the task description and the updated marking
     */
    public String markDone(boolean isNew) {
        isDone = true;
        if (isNew) {
            return printMarking(this, getTag());
        }
        return "";
    }

    /**
     * Unmarks the attribute isDone of the Task and returns the string of the task description
     * and the updated marking
     *
     * @return String of the task description and the updated marking
     */
    public String unmark() {
        isDone = false;
        return printMarking(this, getTag());
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string that contains information of the task to be stored in the hard disk.
     * Used as an argument in Storage class to be written into the file.
     *
     * @return String that represents information of the Task object.
     */
    public String toStore() {
        return " " + getTag() +  " | " + (isDone? "1" : "0")
                +  " | "  + description + "\n";
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return "T";
    }

    /**
     * Updates the task attribute of the task object specified to the new information
     * @param attribute String of the task attribute to be updated, todo task only has description attribute.
     * @param toUpdate String of the new information to be updated
     * @throws BobbyException
     */
    public void update(String attribute, String toUpdate) throws BobbyException {
        if (attribute.equalsIgnoreCase("desc")) {
            description = toUpdate.trim();
        } else {
            throw new BobbyException("Oops. The task you want to update does not have a " + attribute + " attribute.");
        }
    }

    /**
     * Returns a String of the full task description. Called in TaskList's printList method,
     * called for each task in the list.
     * @param num the number of the task in the task list
     * @return String of the task description of the Deadline object.
     */
    public String printTaskDesc(int num){
        assert num >= 1 : "task number should be more than or equals to 1";
        if (num == 1) {
            return String.format("Okies~ Here are the tasks in your list:\n %d.[%s][%s] %s\n",
                    num, getTag(), getStatusIcon(), getDescription());
        } else {
            return String.format(" %d.[%s][%s] %s\n", num, getTag(), getStatusIcon(), getDescription());
        }
    }

    /**
     * Returns a string of the full description of task without any specified task number.
     * Called when updating, adding or deleting tasks to show the user the details of the task that
     * has been added, updated or deleted.
     * @return String of the full description of the task
     */
    public String printFullDesc() {
        return String.format(" [%s][%s] %s\n", getTag(), getStatusIcon(), getDescription());
    }

    /**
     * Returns a String of the description with the number of the matching task.
     * Called by the Storage findFromFile method.
     * @param num number of the task in the matching task list.
     * @return a String of the matching task description with the specified task number
     */
    public String printMatchDesc(int num) {
        assert num >= 1 : "task number should be more than or equals to 1";
        return String.format(" %d.[%s][%s] %s\n", num, getTag(), getStatusIcon(), getDescription());
    }

    public String printMarking(Task task, String tag) {
        if (isDone) {
            return "Wow, well done! I've marked this task as done:\n" +
                    String.format(" [%s][%s] %s\n", tag, task.getStatusIcon(), task.getDescription());
        } else {
            return "Ok, I've marked this task as not done yet:\n" +
                    String.format(" [%s][%s] %s\n", tag, task.getStatusIcon(), task.getDescription());
        }
    }
}
