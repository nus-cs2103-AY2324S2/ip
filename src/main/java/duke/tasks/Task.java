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

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public final boolean nameContains(String str) {
        return this.name.contains(str);
    }

    public String describe() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public String toStorageString() {
        return String.format("%s,%s", this.name, this.isDone ? "T" : "F");
    }

    public static Task fromStorageString(String str) throws DukeException {
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
