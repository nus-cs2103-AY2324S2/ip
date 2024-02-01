package commands;

import java.io.IOException;

import dave.Storage;
import dave.TaskList;
import dave.Ui;
import tasks.Todo;

public class AddTodoCommand extends Command {
    private Todo toAdd;

    public AddTodoCommand(String taskName) {
        this.toAdd = new Todo(taskName);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(this.toAdd);
        try {
            storage.saveTask(this.toAdd);
        } catch (IOException exc) {
            System.out.println("Dave could not write the new task to the output file");
        }
        ui.showTaskAdded(this.toAdd, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
