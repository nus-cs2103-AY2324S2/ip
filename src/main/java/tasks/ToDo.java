package tasks;

import exceptions.InvalidFormatException;
import exceptions.LeluException;

/**
 * This class represents a ToDo task by encapsulating information about a specific task,
 * including the description of the task.
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }


    /**
     * Formats the details of the ToDo object as a String to be written to a text file.
     *
     * @return A String containing the description of the ToDo object.
     */
    public String saveFormat() {
        int check = this.isCompleted ? 1 : 0;
        return String.format("T | %d | %s \n", check, this.taskName);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
