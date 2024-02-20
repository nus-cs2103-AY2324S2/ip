package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Basic template for tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor.
     * @param description Task name or description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Update if task is done.
     * @param isDone    Marks task as completed/uncompleted. [True: complete, False: uncompleted]
     */
    public void updateIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if task description matches string.
     *
     * @param match String to search for within description.
     * @return hasMatch     True: string is found within description.
     *                      False: string is not found within description.
     */
    public boolean isMatchingDescription(String match) {
        return this.description.contains(match);
    }

    /**
     * Parses and converts string to LocalDateTime object.
     *
     * @param dateTime String containing date and time.
     * @return dateTime     LocalDateTime object.
     */
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
                    } catch (DateTimeParseException dt) {
                        // ...
                    }
                }
            }
        }

        return null;
    }

    /**
     * Formats Task as a string to be saved to file.
     * @return saveTask     Returns the task as a string in the format compatible with file.
     */
    public String saveFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.description;
    }
}
