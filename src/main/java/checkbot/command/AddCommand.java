package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.SaveFileException;
import checkbot.task.Deadline;
import checkbot.task.Event;
import checkbot.task.Task;
import checkbot.task.Todo;
import checkbot.task.TodoList;

/**
 * Represents a command to add a Task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

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
    public void execute(TodoList todoList, Storage storage, Ui ui) throws SaveFileException {
        todoList.addTask(task);
        storage.saveTasks(todoList);
        ui.showAddedTaskMessage(task, todoList.getLength());
    }
}
