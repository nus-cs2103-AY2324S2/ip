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
        // assert false : "Execution should never reach this point!";
    }

    /**
     * Search Task in ArrayList based on Date and Time
     * @param input Date and Time to search
     * @param taskList ArrayList with Tasks
     */
    public static String execSearchTask(String input, List<Task> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        int splitFirstWhitespace = 2;
        int contentIdx = 1;
        try {
            String[] wordPartition = input.split(" ", splitFirstWhitespace);
            String content = wordPartition[contentIdx];

            LocalDateTime searchDate = InsertTask.dateConversion(content);
            List<Task> output = new ArrayList<>();

            for (Task act : taskList) {
                int taskTypeIdx = 1;
                char taskType = act.toString().charAt(taskTypeIdx);

                if (taskType == 'D') {
                    Deadline task = (Deadline) act;
                    if (task.getBy().isEqual(searchDate)) {
                        output.add(act);
                    }

                } else if (taskType == 'E') {
                    Event task = (Event) act;
                    if (task.getFrom().isEqual(searchDate) || task.getTo().isEqual(searchDate)) {
                        output.add(act);
                    }
                }
            }

            if (output.isEmpty()) {
                response.append("No event on this date").append("\n");
            } else {
                for (Task act : output) {
                    response.append(act).append("\n");
                }
            }

        } catch (TaylorException err) {
            throw new TaylorException(err.getMessage());
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new TaylorException("Description is empty. (Format: SEARCH <YYYY-MM-DD HHmm>");
        }

        return response.toString();
    }
}
