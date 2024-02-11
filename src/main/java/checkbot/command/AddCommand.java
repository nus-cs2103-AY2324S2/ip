package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.SaveFileException;
import checkbot.task.Deadline;
import checkbot.task.Event;
import checkbot.task.Task;
import checkbot.task.Todo;
import checkbot.task.TodoList;

public class AddCommand extends Command {
    Task task;

    public AddCommand(String name) {
        task = new Todo(name);
    }

    public AddCommand(String name, String by) {
        task = new Deadline(name, by);
    }

    public AddCommand(String name, String from, String to) {
        task = new Event(name, from, to);
    }

    @Override
    public void executeCommand(TodoList todoList, Storage storage, Ui ui) throws SaveFileException {
        todoList.addTask(task);
        storage.saveTasks(todoList);
        ui.showAddedTaskMessage(task, todoList.getLength());
    }
}
