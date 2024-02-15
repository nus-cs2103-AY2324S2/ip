package alpa.commands;

import alpa.exceptions.AlpaException;
import alpa.utils.Storage;
import alpa.tasks.ToDo;
import alpa.tasks.TaskList;
import alpa.ui.Ui;

public class TodoCommand implements Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
        if (description.isEmpty()) {
            throw new AlpaException("\nBaa-ad news, human! The description of a todo cannot be empty.");
        }
        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        ui.showAddedTask(todo, taskList.getSize());
        storage.saveTasks(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

