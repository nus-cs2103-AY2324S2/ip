package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String name) {
        this.description = name;
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        Task newTask = Task.createTask(TaskType.TODO, description);
        list.add(newTask);
        storage.save(list);
        ui.showMessage("added new ToDo: " + newTask);
        ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
    }
}