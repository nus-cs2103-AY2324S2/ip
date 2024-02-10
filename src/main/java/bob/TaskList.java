package bob;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    public static int getSize() {
        return TASKS.size();
    }

    public static Task mark(int taskIndex, boolean isDone) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TaskList.getSize()) {
            throw new InvalidTaskIndexException();
        }

        Task task = TASKS.get(taskIndex);
        task.setDone(isDone);

        return task;
    }

    public static Task delete(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TaskList.getSize()) {
            throw new InvalidTaskIndexException();
        }

        Task task = TASKS.get(taskIndex);
        TASKS.remove(taskIndex);

        Storage.save(TASKS, false);

        return task;
    }

    public static void list() {
        Ui.list(TASKS);
    }

    public static Task addTodo(String description) {
        Task task = new Todo(description);
        TASKS.add(task);
        return task;
    }

    public static Task addDeadline(String description, LocalDateTime by) {
        Task task = new Deadline(description, by);
        TASKS.add(task);
        return task;
    }

    public static Task addEvent(String description, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        TASKS.add(task);
        return task;
    }

    public static void save(boolean isAppend) {
        Storage.save(TASKS, isAppend);
    }
}
