package helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import exceptions.TaylorException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;

/**
 * To insert task into the list
 */
public class TaskInsertion {
    private TaskInsertion() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Add todo task
     * @param content : what to do?
     * @param taskList list of todo tasks
     */
    public static String todoTask(String content, List<Task> taskList) {
        StringBuilder response = new StringBuilder();

        Todo task = new Todo(content);
        Ui.addTask(response, taskList, task);

        return response.toString();
    }

    /**
     * Add Deadline task
     * @param content : what to do? by when?
     * @param taskList list of deadline task
     * @throws TaylorException invalid user input
     */
    public static String deadlineTask(String content, List<Task> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        try {
            String[] deadlineWordPartition = WordsSplit.separateWords(content, "/by", false);
            int actionIdx = 0;
            int timeIdx = 1;
            String action = WordsSplit.getWord(deadlineWordPartition, actionIdx);
            String time = WordsSplit.getWord(deadlineWordPartition, timeIdx);

            Deadline dl = new Deadline(action, dateConversion(time.trim()));
            Ui.addTask(response, taskList, dl);

        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Invalid format. "
                    + "Please type in the following format: deadline <action> /by <time>");
        }
        return response.toString();
    }

    /**
     * Add event
     * @param content : what to do? From when to when?
     * @param taskList list of events task
     * @throws TaylorException Invalid user input
     */
    public static String eventTask(String content, List<Task> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        try {
            // Separate the action and the 2 times
            String[] eventWordPartition = WordsSplit.separateWords(content, "/from", false);
            int actionIdx = 0;
            int timeIdx = 1;
            String action = WordsSplit.getWord(eventWordPartition, actionIdx);
            String time = WordsSplit.getWord(eventWordPartition, timeIdx);

            try {
                String[] timePartition = WordsSplit.separateWords(time, "/to", false);
                int fromIdx = 0;
                int toIdx = 1;
                String fromTime = WordsSplit.getWord(timePartition, fromIdx);
                String toTime = WordsSplit.getWord(timePartition, toIdx);

                LocalDateTime formattedFromTime = dateConversion(fromTime.trim());
                LocalDateTime formattedToTime = dateConversion(toTime.trim());

                CheckValid.checkTime(formattedFromTime, formattedToTime);

                Event eve = new Event(action, formattedFromTime, formattedToTime);
                Ui.addTask(response, taskList, eve);

            } catch (ArrayIndexOutOfBoundsException err) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "event <action> /from <time> /to <time>");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Invalid format. Please type in the following format: "
                    + "event <action> /from <time> /to <time>");
        }
        return response.toString();
    }

    /**
     * Convert input String for Date/Time to correct format
     * @param inputDate User input for Date/Time to be searched
     * @return Correct format for Date/Time
     * @throws TaylorException if Date/Time is invalid
     */
    public static LocalDateTime dateConversion(String inputDate) throws TaylorException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm",
                    Locale.ENGLISH);

            return LocalDateTime.parse(inputDate, formatter);
        } catch (Exception e) {
            throw new TaylorException("Please include <time> as: YYYY-MM-DD HHmm");
        }
    }
}
