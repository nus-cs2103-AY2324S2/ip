package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Arrays;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getSaveTask() {
        return (isDone ? "1" : "0") + " | " + description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public LocalDateTime parseDateTime(String dateTime) {
        List<String> separators = Arrays.asList("-", "/");
        List<String> dateCombinations = Arrays.asList("dd_MM_yyyy ", "MM_dd_yyyy ", "yyyy_dd_MM ", "yyyy_MM_dd ");
        List<String> timeCombinations = Arrays.asList("HH:mm", "HHmm", "hh:mm a");


        for (String d : dateCombinations) {
            for (String t : timeCombinations) {
                for (String s : separators) {
                    try {
                        String pattern = d.replaceAll("_", s) + t;
                        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
                    } catch (DateTimeParseException dt) { }
                }
            }
        }

        return null;
    }

    public boolean isMatchingDescription(String match) {
        return this.description.contains(match);
    }
}