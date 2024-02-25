package taskhelper;

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
 * Inserts task into the list.
 */
public class TaskInsertion {
    private TaskInsertion() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Adds todo task.
     *
     * @param content : what to do?
     * @param taskList list of todo tasks
     */
    public static StringBuilder todoTask(String content, List<Task> taskList) {
        StringBuilder response = new StringBuilder();

        try {
            Todo task = new Todo(content);
            Ui.addTask(response, taskList, task);
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("\n"
                    + "In the shadows of the night I roam,\n"
                    + "I find myself in silence steep\n"
                    + "==============================\n"
                + "Please type in the following format: deadline <action> /by <time>");
        }
        return response;
    }

    /**
     * Adds Deadline task.
     *
     * @param content : what to do? by when?
     * @param taskList list of deadline task
     * @throws TaylorException invalid user input
     */
    public static StringBuilder deadlineTask(String content, List<Task> taskList) throws TaylorException {
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
            throw new TaylorException("\n"
                    + "Through the words may falter, unsure,\n"
                    + "In the silence, I'll endure.\n"
                    + "==============================\n"
                    + "Please type in the following format: deadline <action> /by <time>");
        }
        return response;
    }

    /**
     * Adds event task.
     *
     * @param content : what to do? From when to when?
     * @param taskList list of events task
     * @throws TaylorException Invalid user input
     */
    public static StringBuilder eventTask(String content, List<Task> taskList) throws TaylorException {
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

                System.out.println(formattedFromTime);
                System.out.println(formattedToTime);
                CheckValid.checkTime(formattedFromTime, formattedToTime);

                Event eve = new Event(action, formattedFromTime, formattedToTime);
                Ui.addTask(response, taskList, eve);

            } catch (ArrayIndexOutOfBoundsException err) {
                throw new TaylorException("\n"
                        + "Whispers of meaning, just out of reach,\n"
                        + "Like a melody that I cannot teach.\n"
                        + "Invalid format. Please type in the following format: "
                        + "event <action> /from <time> /to <time>");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("\n"
                    + "I'll listen close to the quiet refrain,\n"
                    + "For in the emptiness, there's so much to gain.\n"
                    + "Invalid format. Please type in the following format: "
                    + "event <action> /from <time> /to <time>");
        }
        return response;
    }

    /**
     * Converts input String for Date/Time to correct format.
     *
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
            throw new TaylorException("\n"
                    + "For in the stillnes, I'll find my way,\n"
                    + "Through the darkness, to the light of day.\n"
                    + "==============================\n"
                    + "Please include <time> as: YYYY-MM-DD HHmm");
        }
    }
}
