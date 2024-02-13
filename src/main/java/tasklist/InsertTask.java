package tasklist;

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
        // throw new AssertionError("Constructor is not allowed");
        assert false : "Execution should never reach this point!";
    }

    /**
     * Execute inserting the tasks
     * @param input User input
     * @param taskList
     * @throws TaylorException
     */
    public static String execInsertTask(String input, List<Task> taskList) throws TaylorException {
        String response = null;
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
            response = todoTask(content, taskList);
            break;
        case DEADLINE:
            response = deadlineTask(content, taskList);
            break;
        case EVENT:
            response = eventTask(content, taskList);
            break;
        default:
            throw new TaylorException("Invalid Task Type");
        }
        return response;
    }

    /**
     * Add todo
     * @param content : what to do?
     * @param taskList
     */
    public static String todoTask(String content, List<Task> taskList) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        Todo task = new Todo(content);
        // Add todoTask into ArrayList
        taskList.add(task);
        response.append(task).append("\n");
        response.append("Now you have ").append(taskList.size()).append(" tasks in the list.").append("\n");
        return response.toString();
    }

    /**
     * Add deadline
     * @param content : what to do? by when?
     * @param taskList
     * @throws TaylorException
     */
    public static String deadlineTask(String content, List<Task> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
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
            response.append("Got it. I've added this task:\n");
            // Add deadline Task into ArrayList
            taskList.add(dl);
            response.append(dl).append("\n");
            response.append("Now you have ").append(taskList.size()).append(" tasks in the list.").append("\n");

        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Invalid format. "
                    + "Please type in the following format: deadline <action> /by <time>");
        }
        return response.toString();
    }

    /**
     * Add event
     * @param content : what to do? From when to when?
     * @param taskList
     * @throws TaylorException
     */
    public static String eventTask(String content, List<Task> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
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
                response.append(eve).append("\n");
                response.append("Now you have ").append(taskList.size()).append(" tasks in the list.").append("\n");

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
