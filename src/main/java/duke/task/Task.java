package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.helpers.MyDateTime;

/**
 * duke.task.Task is a class with description, can record whether a task is done or not done.
 */
public abstract class Task {
    /** Description of the task. */
    private String description;
    /** Record a task is done or not done. */
    private boolean isDone;

    /**
     * Constructor of task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of task class.
     *
     * @param description The description of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Factory method to create TODO.
     *
     * @return duke.task.ToDo task.
     */
    public static Task of(String type, boolean isDone, String description) throws DukeException {
        if (type.equals(CommandType.TODO.toString())) {
            return new ToDo(description, isDone);
        } else {
            throw new DukeException("duke.helpers.Storage Format Issue");
        }
    }

    /**
     * Factory method to create duke.task.Deadline.
     *
     * @return duke.task.Deadline task.
     */
    public static Task of(String type, boolean isDone, String description, String deadline) throws DukeException {
        if (type.equals(CommandType.DEADLINE.toString())) {
            LocalDateTime datetime = MyDateTime.convertDateTime(deadline);
            return new Deadline(description, datetime, isDone);
        } else {
            throw new DukeException("duke.helpers.Storage Format Issue");
        }
    }

    /**
     * Factory method to create duke.task.Event.
     *
     * @return duke.task.Event task.
     */
    public static Task of(String type, boolean isDone, String description,
                          String from, String to) throws DukeException {
        if (type.equals(CommandType.EVENT.toString())) {
            return new Event(description, MyDateTime.convertDateTime(from), MyDateTime.convertDateTime(to), isDone);
        } else {
            throw new DukeException("duke.helpers.Storage Format Issue");
        }
    }

    /**
     * Returns string of marks a task as done.
     * @return string of action.
     */
    public String markDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n" + this.toString();
    }


    /**
     * Returns string of marks a task as not done.
     * @return string of action.
     */
    public String markUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet\n" + this.toString();
    }


    /**
     * Returns String representation of task.
     *
     * @return string representation of task for done and not done task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns integer representation of isDone.
     *
     * @return integer 1 - done, 0 - not done.
     */
    public int isDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns String representation for storage.
     *
     * @return String representation for storage of duke.task.ToDo task.
     */
    public String toStorageString() {
        return this.isDone() + " , " + this.description;
    };

    /**
     * Returns boolean value of whether this task related to the given date.
     *
     * @param date
     * @return false (given task does not have date attribute).
     */
    public boolean checkDate(LocalDate date) {
        return false;
    }

    /**
     * Returns boolean value of whether this task match to the given word.
     *
     * @param word Matching word.
     * @return true if task have matching word, false if task dont have matching word.
     */
    public boolean checkDescription(String word) {
        return this.description.contains(word);
    }
}
