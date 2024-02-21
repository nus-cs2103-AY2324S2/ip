package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.DukeException;

public abstract class Task {
    private static final String UNEXPECTED_TYPE_MSG = "unexpected type string %s";
    private static final String UNEXPECTED_DONE_MSG = "unexpected done string %s";
    private static final String MISSING_FIELD_MSG = "expected a type identifier, but none was given";

    protected final String name;
    protected boolean isDone;

    public static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a, M-dd-yyyy");

    /**
     * Task constructor method. Initialises name.
     * @param name The name of the task.
     */
    public Task(String name) {
        assert name != null : "name cannot be null";
        assert !name.contains("\n") : "name cannot contain newline characters";
        this.name = name;
        this.isDone = false;
    }

    /** Marks this task as done. */
    public void mark() {
        this.isDone = true;
    }

    /** Marks this task as not done. */
    public void unmark() {
        this.isDone = false;
    }

    /** Returns true if the name of this task contains the given string. */
    public final boolean nameContains(String str) {
        return this.name.contains(str);
    }

    /**
     * Returns a string describing details of this task. Intended for printing to console.
     * @return a string containing details of this task.
     */
    public String describe() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns a string representation containing all details required to reconstruct this part of the task. Intended
     * for writing to storage.
     * @return a String that shows the name and done status of the task.
     */
    public String toStorageString() {
        return String.format("%s,%s", this.name, this.isDone ? "T" : "F");
    }

    public static Task fromStorageString(String str) throws DukeException {
        assert str != null : "str cannot be null";
        Scanner sc = new Scanner(str);
        String typeStr;
        String nameStr;
        String doneStr;
        Task t;

        sc.useDelimiter(",");
        try {
            typeStr = sc.next();
            nameStr = sc.next();
            doneStr = sc.next();

            switch (typeStr) {
            case "T":
                t = new ToDo(nameStr);
                break;
            case "D":
                t = new Deadline(nameStr, sc.next());
                break;
            case "E":
                t = new Event(nameStr, sc.next(), sc.next());
                break;
            default:
                throw new DukeException(String.format(UNEXPECTED_TYPE_MSG, typeStr));
            }

            switch (doneStr) {
            case "T":
                t.isDone = true;
                break;
            case "F":
                t.isDone = false;
                break;
            default:
                throw new DukeException(String.format(UNEXPECTED_DONE_MSG, doneStr));
            }

            return t;
        } catch (NoSuchElementException e) {
            throw new DukeException(MISSING_FIELD_MSG);
        }
    }
}
