package gulie.task;

import gulie.GulieException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task that can be marked as complete.
 */
public abstract class Task {
    private String name;
    private Boolean isMarked;

    /**
     * A constructor for Task.
     * @param name
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * A constructor for Task.
     * @param name
     * @param mark
     */
    public Task(String name, boolean mark) {
        this.name = name;
        this.isMarked = mark;
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Returns a String suitable for use in a save file.
     * @return
     */
    public String toSaveString() {
        return String.format("%s\t%s", isMarked ? "1" : "0", name);
    }

    /**
     * Returns true if the keyword is in this Task.
     *
     * @param keyword
     * @return true if the keyword is in this Task
     */
    public boolean hasKeyword(String keyword) {
        return name.contains(keyword);
    }

    /**
     * Generates a Task from a save string.
     * @param str
     * @return
     * @throws GulieException If the input string not follow the save format.
     */
     public static Task fromSaveString(String str) throws GulieException {
        String[] spl = str.split("\t");
        if (!spl[1].equals("0") && !spl[1].equals("1")) {
            throw new GulieException("This task is corrupted.");
        }
        try {
            switch (spl[0]) {
            case "T":
                if (spl.length != 3) {
                    throw new GulieException("This task is corrupted.");
                }
                return new Todo(spl[2], spl[1].equals("1"));
            case "D":
                if (spl.length != 4) {
                    throw new GulieException("This task is corrupted.");
                }
                return new Deadline(spl[2], LocalDateTime.parse(spl[3]), spl[1].equals("1"));
            case "E":
                if (spl.length != 5) {
                    throw new GulieException("This task is corrupted.");
                }
                return new Event(spl[2], LocalDateTime.parse(spl[3]), LocalDateTime.parse(spl[4]), spl[1].equals("1"));
            default:
                throw new GulieException("This task is corrupted.");
            }
        } catch (DateTimeParseException e) {
            throw new GulieException("This task is corrupted.");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isMarked ? "X" : " ", name);
    }

    /**
     * Returngs a string representaiton of the task using a specified DateTimeFormatter.
     * @param dtf
     * @return
     */
    public abstract String toString(DateTimeFormatter dtf);
}
