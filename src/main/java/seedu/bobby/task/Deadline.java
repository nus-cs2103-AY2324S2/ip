package seedu.bobby.task;

import seedu.bobby.BobbyException;
import seedu.bobby.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h1> Deadline </h1>
 * This Deadline class is a subclass of the Task class, holding all the attributes and methods
 * of its parent class. This program also has methods that overrides the methods from its parent class Task,
 * to handle the cases differently when the task is a deadline and more specific than a normal todo task.
 * Deadline class objects have an attribute LocalDateTime deadline. This class handles all the functionalities
 * of a Deadline object, such as the printing of the task description and the function that returns the data to be stored.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Deadline extends Task{
    private LocalDateTime deadline;
    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getTag() {
        return "D";
    }

    /**
     * Updates the task attribute of the Deadline object specified to the new information
     * @param attribute String of the task attribute to be updated, possible attributes for deadline task
     *                  are description and deadline.
     * @param toUpdate String of the new information to be updated
     * @throws BobbyException
     */
    @Override
    public void update(String attribute, String toUpdate) throws BobbyException {
        if (attribute.equalsIgnoreCase("desc")) {
            description = toUpdate.trim();
        } else if (attribute.equalsIgnoreCase("by")) {
            deadline = Parser.parseDeadline(toUpdate.trim());
        } else {
            throw new BobbyException("Oops. The task you want to update does not have a " + attribute + " attribute.");
        }
    }

    /**
     * Returns a string that contains information of the Deadline task to be stored in the hard disk.
     * Used as an argument in Storage class to be written into the file.
     *
     * @return String that represents information of the Deadline object.
     */
    @Override
    public String toStore() {
        return " D | " + (isDone ? "1" : "0") +  " | "  + description + " | " + parseDateTime() + "\n";
    }

    /**
     * Returns a string that contains the deadline in a different format to be stored and shown
     * to the user when they display the tasks.
     *
     * @return String parsed from the deadline attribute of this Deadline object, representing
     * the date and time in a different format.
     */
    private String parseDateTime() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM uuuu HHmm");
        return deadline.format(outputFormatter);
    }

    /**
     * Returns a String of the full task description. Called in TaskList's printList method,
     * called for each task in the list.
     * @param num the number of the task in the task list
     * @return String of the task description of the Deadline object.
     */
    @Override
    public String printTaskDesc(int num){
        assert num >= 1 : "task number should be more than or equals to 1";
        if (num == 1) {
            return String.format("Okies~ Here are the tasks in your list:\n %d.[%s][%s] %s (by: %s)\n",
                    num, getTag(), getStatusIcon(), getDescription(), parseDateTime());
        } else {
            return String.format(" %d.[%s][%s] %s (by: %s)\n",
                    num, getTag(), getStatusIcon(), getDescription(), parseDateTime());
        }
    }

    /**
     * Returns a String of the description with the number of the matching task.
     * Called by the Storage findFromFile method.
     * @param num number of the task in the matching task list.
     * @return a String of the matching task description with the specified task number
     */
    @Override
    public String printMatchDesc(int num) {
        assert num >= 1 : "task number should be more than or equals to 1";
        return String.format(" %d.[%s][%s] %s (by: %s)\n",
                num, getTag(), getStatusIcon(), getDescription(), parseDateTime());
    }

    /**
     * Returns a string of the full description of task without any specified task number.
     * Called when updating, adding or deleting tasks to show the user the details of the task that
     * has been added, updated or deleted.
     * @return String of the full description of the task
     */
    @Override
    public String printFullDesc() {
        return String.format(" [%s][%s] %s (by: %s)\n",
                getTag(), getStatusIcon(), getDescription(), parseDateTime());
    }
}
