package helper;

import tasks.Task;

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
}
