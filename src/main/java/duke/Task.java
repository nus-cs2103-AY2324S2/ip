package duke;

/**
 * Represent a class Task that stores some information to be used with a Chatbot
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Task {
    /**
     * A Task instance contains a description to be read out
     */
    private String description;
    /**
     * A Task instance contains a label to determine whether it is marked or not
     */
    private boolean isDone;

    /**
     * Constructor for a Task instance
     *
     * @param description to be used to identify a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Determines if a task is marked/unmarked for list printing later
     *
     * @return  X or blank space in relation to whether task is marked/unmarked
     */
    public String getStatusIcon() {
        // mark done task with X
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Prints Task description in Task Array
     *
     * @return a string representing the task description
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]"
                + this.description;
    }

    /**
     * Prints Task description in Task Array when updating the data file
     *
     * @return a string representing the task description
     */

    public String toString(boolean update) {

        int isDoneInt = 0;

        if (this.isDone == true) {
            isDoneInt = 1;
        }

        return isDoneInt + "@" + this.description;
    }

    /**
     * Marks a valid Task in the Task array
     *
     * @return the description of the Task after being marked
     * @throws DukeException when task is already marked
     */
    public String markAsDone() throws DukeException {
        if (this.isDone == true) {
            throw new DukeException("This task is already completed.\n");
        } else {
            this.isDone = true;
            return "Very well. This task is now completed.\n"
                    + this.toString()
                    + "\n";
        }
    }

    /**
     * Unmarks a valid Task in the Task array
     *
     * @return the description of the Task after being unmarked
     * @throws DukeException when task is already unmarked
     */
    public String unmarkTask() throws DukeException {
        if (this.isDone == false) {
            throw new DukeException("This task is already unmarked.\n");
        } else {
            this.isDone = false;
            return "Very well. This task is now marked as not completed.\n"
                    + this.toString()
                    + "\n";
        }
    }

    /**
     * Snoozes a valid Task in the Task array
     *
     * @return the description of the Task after being snoozed
     * @throws DukeException when task cannot be snoozed
     */
    public String snoozeTask() throws DukeException {
        return "Very well. This task is now snoozed.\n"
                + this.toString()
                + "\n";
    }
}
