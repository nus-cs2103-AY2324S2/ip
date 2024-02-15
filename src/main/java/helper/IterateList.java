package helper;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class IterateList {
    private IterateList() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static List<Task> findContains(List<List<Task>> taskList, String keyword) {
        return taskList.stream()
                .flatMap(List::stream)
                .filter(task -> task.getExecute().contains(keyword))
                .collect(Collectors.toList());
    }

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

    public static List<Task> retrieveList(List<List<Task>> taskList, int idx) {
        return taskList.get(idx);
    }
}
