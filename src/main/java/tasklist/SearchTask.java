package tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.TaylorException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
 * To search tasks based on Date and Time
 */
public class SearchTask {
    /**
     * No constructor needed
     */
    private SearchTask() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Search Task in ArrayList based on Date and Time
     * @param input Date and Time to search
     * @param taskList ArrayList with Tasks
     */
    public static void execSearchTask(String input, List<Task> taskList) {
        try {
            String[] parts = input.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isBlank()) {
                throw new TaylorException("The description of the task is empty.");
            }
            String content = parts[1];
            LocalDateTime searchDate = InsertTask.dateConversion(content);
            List<Task> output = new ArrayList<>();

            for (Task act : taskList) {
                char whichTask = act.toString().charAt(1);

                if (whichTask == 'D') {
                    Deadline task = (Deadline) act;
                    if (task.getBy().isEqual(searchDate)) {
                        output.add(act);
                    }

                } else if (whichTask == 'E') {
                    Event task = (Event) act;
                    if (task.getFrom().isEqual(searchDate) || task.getTo().isEqual(searchDate)) {
                        output.add(act);
                    }
                }
            }
            if (output.isEmpty()) {
                System.out.println("No event on this date");
            } else {
                for (Task act : output) {
                    System.out.println(act);
                }
            }
        } catch (TaylorException err) {
            throw new TaylorException(err.getMessage());
        }
    }
}
