package louie.tasks;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import louie.LouieException;

public abstract class Task {
    private static final String UNEXPECTED_TYPE_MSG = "unexpected type string %s";
    private static final String UNEXPECTED_DONE_MSG = "unexpected done string %s";
    private static final String UNEXPECTED_PRIORITY_MSG = "unexpected priority string %s";
    private static final String MISSING_TYPE_MSG = "expected a type identifier, but none was given";
    private static final String MISSING_NAME_MSG = "expected a name, but none was given";
    private static final String MISSING_DONE_MSG = "expected an done status, but none was given";
    private static final String MISSING_PRIORITY_MSG = "expected a priority status, but none was given";
    private static final String MISSING_BY_DATE_MSG = "expected a deadline date, but none was given";
    private static final String MISSING_FROM_DATE_MSG = "expected a start date, but none was given";
    private static final String MISSING_TO_DATE_MSG = "expected an end date, but none was given";
    private static final String UNEXPECTED_EXTRA = "unexpected extra field in storage string";

    protected final String name;
    protected boolean isDone;
    protected Priority priority;

    public static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a, M-dd-yyyy");

    /**
     * Task constructor method. Initialises name.
     * @param name The name of the task.
     */
    public Task(String name) throws LouieException {
        assert name != null : "name cannot be null";
        assert !name.contains("\n") : "name cannot contain newline characters";
        if (name.contains(",")) {
            throw new LouieException("name cannot contain commas");
        }
        this.name = name;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /** Marks this task as done. */
    public void mark() {
        this.isDone = true;
    }

    /** Marks this task as not done. */
    public void unmark() {
        this.isDone = false;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /** Returns true if the name of this task contains the given string. */
    public final boolean nameContains(String str) {
        return this.name.contains(str);
    }

    /**
     * Helper method to get the done status of this task.
     * @return "X" if isDone, " " otherwise.
     */
    private String getDoneStatus() {
        return this.isDone ? "X" : " ";
    }

    private String getPriorityStatus() {
        switch (this.priority) {
        case HIGH:
            return " \u2605";
        case LOW:
            return "";
        default:
            throw new IllegalStateException("Unexpected priority: " + this.priority);
        }
    }

    /**
     * Returns a string describing details of this task. Intended for printing to console.
     * @return a string containing details of this task.
     */
    public String describe() {
        return String.format("[%s]%s %s", this.getDoneStatus(), this.getPriorityStatus(), this.name);
    }

    /**
     * Returns a string representation containing all details required to reconstruct this part of the task. Intended
     * for writing to storage.
     * @return a String that shows the name and done status of the task.
     */
    public String toStorageString() {
        String doneStr = this.isDone ? "T" : "F";
        String priorityStr;

        switch (this.priority) {
        case HIGH:
            priorityStr = "H";
            break;
        case LOW:
            priorityStr = "L";
            break;
        default:
            throw new IllegalStateException("Unexpected priority: " + this.priority);
        }
        return String.format("%s,%s,%s", this.name, doneStr, priorityStr);
    }

    /**
     * Helper method for fromStorageString to scan a string from the given scanner, or throw a LouieException with the given message.
     * @param sc The scanner to scan from.
     * @param msg The message thrown if the scanner is empty.
     * @return The scanned string.
     * @throws LouieException If the scanner is empty.
     */
    private static String scanOrThrowMsg(Scanner sc, String msg) throws LouieException {
        if (!sc.hasNext()) {
            throw new LouieException(msg);
        }
        return sc.next();
    }

    /**
     * Helper method for fromStorageString to construct a task based on the given type and name strings. The scanner
     * must also be passed in.
     * @param sc The scanner to scan from.
     * @param typeStr The type of the task, which is either "T", "D", or "E".
     * @param nameStr The name of the task.
     * @return The constructed task.
     * @throws LouieException If typeStr is invalid.
     */
    private static Task baseTaskFromString(Scanner sc, String typeStr, String nameStr) throws LouieException {
        Task t;
        switch (typeStr) {
        case "T":
            t = new ToDo(nameStr);
            break;
        case "D":
            t = new Deadline(nameStr, Task.scanOrThrowMsg(sc, MISSING_BY_DATE_MSG));
            break;
        case "E":
            t = new Event(nameStr,
                    Task.scanOrThrowMsg(sc, MISSING_FROM_DATE_MSG),
                    Task.scanOrThrowMsg(sc, MISSING_TO_DATE_MSG));
            break;
        default:
            throw new LouieException(String.format(UNEXPECTED_TYPE_MSG, typeStr));
        }
        return t;
    }

    /**
     * Helper method for fromStorageString to mark a task based on the given string.
     * @param t The task to mark.
     * @param doneStr The string to mark the done status of the task.
     * @throws LouieException If doneStr is not "T" or "F".
     */
    private static void markDoneFromStorage(Task t, String doneStr) throws LouieException {
        assert doneStr != null : "doneStr cannot be null";
        switch (doneStr) {
        case "T":
            t.isDone = true;
            break;
        case "F":
            t.isDone = false;
            break;
        default:
            throw new LouieException(String.format(UNEXPECTED_DONE_MSG, doneStr));
        }
    }

    /**
     * Helper method for fromStorageString to set task priority based on the given string.
     * @param t The task to set priority for.
     * @param priorityStr The string to set the priority of the task.
     * @throws LouieException If priorityStr is not "H" or "L".
     */
    private static void markPriorityFromStorage(Task t, String priorityStr) throws LouieException {
        assert priorityStr != null : "priorityStr cannot be null";
        switch (priorityStr) {
        case "H":
            t.priority = Priority.HIGH;
            break;
        case "L":
            t.priority = Priority.LOW;
            break;
        default:
            throw new LouieException(String.format(UNEXPECTED_PRIORITY_MSG, priorityStr));
        }
    }

    /**
     * Returns a Task object that is reconstructed from the given string from storage.
     * @param str The string to reconstruct the task from.
     * @return The reconstructed task object.
     * @throws LouieException If the string is not in the correct format.
     */
    public static Task fromStorageString(String str) throws LouieException {
        assert str != null : "str cannot be null";
        Scanner sc = new Scanner(str);
        sc.useDelimiter(",");
        String typeStr = scanOrThrowMsg(sc, MISSING_TYPE_MSG);
        String nameStr = scanOrThrowMsg(sc, MISSING_NAME_MSG);
        String doneStr = scanOrThrowMsg(sc, MISSING_DONE_MSG);
        String priorityStr = scanOrThrowMsg(sc, MISSING_PRIORITY_MSG);
        Task t = baseTaskFromString(sc, typeStr, nameStr);

        if (sc.hasNext()) {
            throw new LouieException(UNEXPECTED_EXTRA);
        }

        markDoneFromStorage(t, doneStr);
        markPriorityFromStorage(t, priorityStr);

        return t;
    }
}
