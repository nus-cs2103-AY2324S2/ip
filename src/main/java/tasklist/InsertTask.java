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
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
    }

    /**
     * Execute inserting the tasks
     * @param input User input
     * @param taskList
     * @throws TaylorException
     */
    public static String execInsertTask(String input, List<List<? extends Task>> taskList) throws TaylorException {
        String response = null;

        int splitFirstWhitespace = 2;
        int contentsIdx = 1;
        int actionsIdx = 0;

        String[] wordPartition = input.split(" ", splitFirstWhitespace);
        boolean isContentEmpty = wordPartition[contentsIdx].trim().isBlank();
        if (isContentEmpty) {
            throw new TaylorException("The description of the task is empty.");
        }

        String content = wordPartition[contentsIdx];
        Type type = Type.valueOf(wordPartition[actionsIdx].toUpperCase());

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

        Todo task = new Todo(content);
        taskList.add(task);
        response.append(task).append("\n");

        response.append("Got it. I've added this task:\n");
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
            String[] deadlineWordPartition = content.split("/by");
            int actionIdx = 0;
            int timeIdx = 1;
            String action = deadlineWordPartition[actionIdx];
            String time = deadlineWordPartition[timeIdx];
            boolean isActionEmpty = action.trim().isBlank();
            boolean isTimeEmpty = time.trim().isBlank();
            if (isActionEmpty || isTimeEmpty) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "deadline <action> /by <time>");
            }

            Deadline dl = new Deadline(action, dateConversion(time.trim()));
            taskList.add(dl);
            response.append(dl).append("\n");

            response.append("Got it. I've added this task:\n");
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
            String[] eventWordPartition = content.split("/from");
            int actionIdx = 0;
            int timeIdx = 1;
            String action = eventWordPartition[actionIdx];
            String time = eventWordPartition[timeIdx];
            boolean isActionEmpty = action.trim().isBlank();
            boolean isTimeEmpty = time.trim().isBlank();
            if (isActionEmpty || isTimeEmpty) {
                throw new TaylorException("Invalid format. Please type in the following format: "
                        + "event <action> /from <time> /to <time>");
            }

            try {
                String[] timePartition = time.split("/to");
                int fromIdx = 0;
                int toIdx = 1;
                String fromTime = timePartition[fromIdx];
                String toTime = timePartition[toIdx];

                LocalDateTime formattedFromTime = dateConversion(fromTime.trim());
                LocalDateTime formattedToTime = dateConversion(toTime.trim());

                if (formattedToTime.isBefore(formattedFromTime)) {
                    throw new TaylorException("End time cannot be before start time!");
                }

                Event eve = new Event(action, formattedFromTime, formattedToTime);
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm",
                    Locale.ENGLISH);

            return LocalDateTime.parse(inputDate, formatter);
        } catch (Exception e) {
            throw new TaylorException("Please include <time> as: YYYY-MM-DD HHmm");
        }
    }
}
