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

    @Override
    public String printMatchDesc(int num) {
        assert num >= 1 : "task number should be more than or equals to 1";
        return String.format(" %d.[%s][%s] %s (by: %s)\n",
                num, getTag(), getStatusIcon(), getDescription(), parseDateTime());
    }

    @Override
    public String printFullDesc() {
        return String.format(" [%s][%s] %s (by: %s)\n",
                getTag(), getStatusIcon(), getDescription(), parseDateTime());
    }
}
