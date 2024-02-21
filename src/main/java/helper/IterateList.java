package helper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

/**
 * Class to iterate through the list
 */
public class IterateList {
    private IterateList() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Check database for any Task which contains the keyword
     * @param taskList list of tasks lists
     * @param keyword queried String
     * @return List of task which contains the keyword
     */
    public static List<Task> findContains(List<List<Task>> taskList, String keyword) {
        return taskList.stream()
                .flatMap(List::stream)
                .filter(task -> task.getExecute().contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Search for tasks which contain the queried date & time
     * @param taskList list of tasks lists
     * @param searchDate queried date and time
     * @param output List of task which contains the queried
     */
    public static void searchList(List<List<Task>> taskList, LocalDateTime searchDate, List<Task> output) {
        int deadlineListIdx = 0;
        int eventListIdx = 1;
        List<Task> deadlineList = taskList.get(deadlineListIdx);
        List<Task> eventList = taskList.get(eventListIdx);

        for (Task deadline : deadlineList) {
            Deadline task = (Deadline) deadline;
            if (task.getBy().isEqual(searchDate)) {
                output.add(task);
            }
        }

        for (Task event : eventList) {
            Event task = (Event) event;
            if (task.getFrom().isEqual(searchDate) || task.getTo().isEqual(searchDate)) {
                output.add(task);
            }
        }
    }

    /**
     * Return the required type of Task List (Todo, Event, Deadline)
     * @param taskList list of tasks lists
     * @param taskType type of tasks needed
     * @return Task List queried
     */
    public static List<Task> retrieveList(List<List<Task>> taskList, String taskType) {
        List<Task> lst = new ArrayList<>();
        switch (taskType) {
        case "DEADLINE":
            lst = taskList.get(0);
            break;
        case "EVENT":
            lst = taskList.get(1);
            break;
        case "TODO":
            lst = taskList.get(2);
            break;
        default:
            assert false : "Execution should never reach this point!";
        }
        return lst;
    }
}
