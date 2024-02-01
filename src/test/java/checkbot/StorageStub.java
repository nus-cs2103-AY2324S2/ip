package checkbot;

import checkbot.task.TodoList;

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
