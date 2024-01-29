import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task is a class with description, can record whether a task is done or not done.
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
     * @return ToDo task.
     */
    public static Task of(String type, boolean isDone, String description) throws DukeException {
        if (type.equals(CommandType.TODO.toString())) {
            return new ToDo(description, isDone);
        } else {
            throw new DukeException("Storage Format Issue");
        }
    }

    /**
     * Factory method to create Deadline.
     *
     * @return Deadline task.
     */
    public static Task of(String type, boolean isDone, String description, String deadline) throws DukeException {
        if (type.equals(CommandType.DEADLINE.toString())) {
            LocalDateTime datetime = MyDateTime.convertDateTime(deadline);
            return new Deadline(description, datetime, isDone);
        } else {
            throw new DukeException("Storage Format Issue");
        }
    }

    /**
     * Factory method to create Event.
     *
     * @return Event task.
     */
    public static Task of(String type, boolean isDone, String description, String from, String to) throws DukeException {
        if (type.equals(CommandType.EVENT.toString())) {
            return new Event(description, MyDateTime.convertDateTime(from), MyDateTime.convertDateTime(to), isDone);
        } else {
            throw new DukeException("Storage Format Issue");
        }
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }


    /**
     * Marks a task as undone.
     */
    public void markUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(this);
    }


    /**
     * String representation of task.
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
     * String representation for storage.
     *
     * @return String representation for storage of ToDo task.
     */
    public String toStorageString() {
        return this.isDone() + " " + this.description;
    };

    public boolean checkDate(LocalDate date) {
        return false;
    }
}
