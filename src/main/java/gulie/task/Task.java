package gulie.task;

import gulie.GulieException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    private String name;
    private Boolean mark;
    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean mark) {
        this.name = name;
        this.mark = mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public String toSaveString() {
        return String.format("%s\t%s", mark ? "1" : "0", name);
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
        return String.format("[%s] %s", this.mark ? "X" : " ", this.name);
    }

    public abstract String toString(DateTimeFormatter dtf);
}
