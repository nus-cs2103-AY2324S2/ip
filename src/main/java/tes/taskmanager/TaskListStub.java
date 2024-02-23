package tes.taskmanager;

import tes.command.DateAndTimeFormatterException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskListStub {
    /** Format of the date and time in the command received */
    private static final DateTimeFormatter FORMATTER_RECEIVE = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /** The task list to store tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs a task list to store tasks.
     * Assumes the maximum size of the list to be 100.
     *
     * @param tasks The task list either retrieved from local disk or a new one.
     */
    public TaskListStub(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public String storeDeadline(String task, String by) {
        try {
            LocalDateTime formattedBy = LocalDateTime.parse(by, FORMATTER_RECEIVE);
            Deadline newDeadline = new Deadline(task, formattedBy);
            this.tasks.add(newDeadline);
            return this.tasks.get(0).toString();
        } catch (DateTimeParseException e) {
            return "Wrong format of Date or Time!!!! Here's a quick tip:\n"
                    + "O p e n  Y o u r  B l u r r y  E y e s  W h e n  T y p i n g ! ! !";
        }
    }

    public String storeEvent(String task, String from, String to) {
        try {
            LocalDateTime formattedFrom = LocalDateTime.parse(from, FORMATTER_RECEIVE);
            LocalDateTime formattedTo = LocalDateTime.parse(to, FORMATTER_RECEIVE);
            Event newEvent = new Event(task, formattedFrom, formattedTo);
            this.tasks.add(newEvent);
            return this.tasks.get(0).toString();
        } catch (DateTimeParseException e) {
            return "Wrong format of Date or Time!!!! Here's a quick tip:\n"
                    + "O p e n  Y o u r  B l u r r y  E y e s  W h e n  T y p i n g ! ! !";
        }
    }
}
