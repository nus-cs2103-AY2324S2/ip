package bob;

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

    public static Task add(String taskType, String[] parameters) {
        // TODO: Might be better to have addTodo, addDeadline and addEvent separately
        Task task;
        switch (taskType) {
            case Parser.TODO:
                task = new Todo(parameters[0]);
                break;
            case Parser.DEADLINE:
                task = new Deadline(parameters[0], parameters[1]);
                break;
            default:
                task = new Event(parameters[0], parameters[1], parameters[2]);
        }

        TASKS.add(task);

        return task;
    }

    public static void save(boolean isAppend) {
        Storage.save(TASKS, isAppend);
    }
}
