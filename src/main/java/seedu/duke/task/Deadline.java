package seedu.duke.task;

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
     * Returns a string that contains information of the Deadline task to be stored in the hard disk.
     * Used as an argument in Storage class to be written into the file.
     *
     * @return String that represents information of the Deadline object.
     */
    @Override
    public String toStore() {
        return " D | " + (this.isDone ? "1" : "0") +  " | "  + this.description + " | " + this.parseDateTime() + "\n";
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
        return this.deadline.format(outputFormatter);
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
            } else {
                System.out.printf("      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
            }
        } else {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
                System.out.print("      ________________________________________________________\n");
            } else {
                System.out.printf("      %d.[%s][%s] %s (by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
                System.out.print("      ________________________________________________________\n");
            }
        }
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         [%s][%s] %s (by: %s)\n",
                this.getTag(), this.getStatusIcon(), this.getDescription(), this.parseDateTime());
    }
}
