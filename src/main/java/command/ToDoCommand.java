package command;

import java.io.IOException;

import exception.StorageException;
import storage.Storage;
import task.TaskList;
import task.ToDo;
import ui.UI;

public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UI ui) throws StorageException {
        ToDo toDo = new ToDo(description);
        taskList.addTask(toDo);
        ui.showTaskAdded(toDo, taskList);
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            throw new StorageException("Failed to write to storage file");
        }
    }
}
