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
    public static void exec(String input, List<Task> taskList) throws TaylorException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isBlank()) {
            throw new TaylorException("The description of the task is empty.");
        }
        String content = parts[1];
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
            String[] splitter = content.split("/by");

            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "deadline <action> /by <time>");
            }

            Deadline dl = new Deadline(splitter[0], dateConversion(splitter[1].trim()));
            System.out.println("Got it. I've added this task:");
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
            String[] splitter = content.split("/from");

            if (splitter.length < 2 || splitter[0].trim().isBlank() || splitter[1].trim().isBlank()) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "event <action> /from <time> /to <time>");
            }

            try {
                String[] splitter1 = splitter[1].split("/to");

                if (splitter1.length < 2 || splitter1[0].trim().isBlank() || splitter1[1].trim().isBlank()) {
                    throw new TaylorException("Invalid format. Please type in the following format: "
                            + "event <action> /from <time> /to <time>");
                }
                Event eve = new Event(splitter[0],
                        dateConversion(splitter1[0].trim()), dateConversion(splitter1[1].trim()));
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
