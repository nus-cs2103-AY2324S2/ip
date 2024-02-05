package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import tasks.ToDo;

public class TodoCommand extends Command {
    private String taskDescription;
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo todo = new ToDo(this.taskDescription);
        taskList.addTask(todo);
        storage.saveToFile(taskList);
    }
}
