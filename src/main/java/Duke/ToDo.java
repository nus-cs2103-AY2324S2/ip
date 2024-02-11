package duke;

import java.time.LocalDate;

/**
 * This class represents a ToDo task.
 * It extends the Task class with a specific string representation.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified name.
     *
     * @param name the name of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The returned string includes the task type ([T]) and the string representation of the superclass.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the encoded version of ToDo
     *
     * @return Encoded String version of the ToDo. Currently, uses toString() to encode.
     */
    public String encode() {
        return toString();
    }

    /**
     * Decodes the task details into a ToDo object.
     *
     * @param taskDetails the details of the task to decode
     * @return a new ToDo object with the decoded details
     */
    public static ToDo decode(String taskDetails) {
        return new ToDo(taskDetails);
    }

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof ToDo) {
            return this.name.compareTo(otherTask.name);
        } else {
            return 1;
        }
    }
}