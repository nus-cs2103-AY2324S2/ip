package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;
import bmo.task.Task;
import bmo.task.ToDo;

public class ToDoCommand extends Command {

    private final String desc;

    public ToDoCommand(String desc) {
        this.desc = desc;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(this.desc);
        tasks.add(newTask);
        ui.printAddTask(newTask, tasks.size());
    }
}
