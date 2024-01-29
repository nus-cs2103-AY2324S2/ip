package executes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import exceptions.TaylorException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;


/**
 * To add Task into the ArrayList
 */
public class InsertTask {
    enum Type {
        TODO, DEADLINE, EVENT
    }
    /**
     * No constructor needed
     */
    private InsertTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute inserting the tasks
     * @param input User input
     * @param taskList
     * @throws TaylorException
     */
    public static void execInsertTask(String input, List<Task> taskList) throws TaylorException {
        // Split the UserInput to get the Action to be taken
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isBlank()) {
            throw new TaylorException("The description of the task is empty.");
        }
        // Get the content after the action
        String content = parts[1];
        // Get Action to be taken
        Type type = Type.valueOf(parts[0].toUpperCase());

        switch (type) {
        case TODO:
            todoTask(content, taskList);
            break;
        case DEADLINE:
            deadlineTask(content, taskList);
            break;
        case EVENT:
            eventTask(content, taskList);
            break;
        default:
            throw new TaylorException("Invalid Task Type");
        }

    }

    /**
     * Add todo
     * @param content : what to do?
     * @param taskList
     */
    public static void todoTask(String content, List<Task> taskList) {
        System.out.println("Got it. I've added this task:");
        Todo task = new Todo(content);
        // Add todoTask into ArrayList
        taskList.add(task);
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Add deadline
     * @param content : what to do? by when?
     * @param taskList
     * @throws TaylorException
     */
    public static void deadlineTask(String content, List<Task> taskList) throws TaylorException {
        try {
            // Given the content, get the Time
            String[] splitter = content.split("/by");
            // If there is no time, invalid format
            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "deadline <action> /by <time>");
            }
            // Get action and the time separately
            Deadline dl = new Deadline(splitter[0], dateConversion(splitter[1].trim()));
            System.out.println("Got it. I've added this task:");
            // Add deadline Task into ArrayList
            taskList.add(dl);
            System.out.println(dl);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Invalid format. "
                    + "Please type in the following format: deadline <action> /by <time>");
        }
    }

    /**
     * Add event
     * @param content : what to do? From when to when?
     * @param taskList
     * @throws TaylorException
     */
    public static void eventTask(String content, List<Task> taskList) throws TaylorException {
        try {
            // Separate the action and the 2 times
            String[] splitter = content.split("/from");
            // Invalid format if no action, or no time at the back
            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "event <action> /from <time> /to <time>");
            }

            try {
                // Get the from and to time separately
                String[] splitter1 = splitter[1].split("/to");
                // If no such time, invalid format
                if (splitter1.length < 2 || splitter1[0].trim().isBlank() || splitter1[1].trim().isBlank()) {
                    throw new TaylorException("Invalid format. Please type in the following format: "
                            + "event <action> /from <time> /to <time>");
                }
                Event eve = new Event(splitter[0],
                        dateConversion(splitter1[0].trim()), dateConversion(splitter1[1].trim()));
                // Add event Task into ArrayList
                taskList.add(eve);

                System.out.println(eve);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException err) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "event <action> /from <time> /to <time>");
            }
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Invalid format. Please type in the following format: "
                    + "event <action> /from <time> /to <time>");
        }
    }

    /**
     * Convert input String for Date/Time to correct format
     * @param inputDate User input for Date/Time to be searched
     * @return Correct format for Date/Time
     * @throws TaylorException if Date/Time is invalid
     */
    public static LocalDateTime dateConversion(String inputDate) throws TaylorException {
        try {
            // Convert the Date Time Format
            LocalDateTime date = LocalDateTime.parse(inputDate,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm",
                            Locale.ENGLISH));
            if (date.isBefore(LocalDateTime.now())) {
                throw new TaylorException("Date input is in the past");
            }

            return date;
        } catch (Exception e) {
            throw new TaylorException(e.getMessage());
        }
    }
}
