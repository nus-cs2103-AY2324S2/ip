package checkbot;

import checkbot.task.TodoList;

/**
 * A Stub for the Storage class that overwrites methods that involves saving and loading data from the disk.
 */
public class StorageStub extends Storage {
    public StorageStub() {
        super("");
    }

    @Override
    public void saveTasks(TodoList todoList) {
    }

    @Override
    public TodoList loadTasks() {
        return new TodoList();
    }
}
