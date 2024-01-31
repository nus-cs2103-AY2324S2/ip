package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.ToDo;

public class AddToDoCommand extends Command {
    private String description;

    public AddToDoCommand(TaskList tasks, Ui ui, Storage storage, String description) {
        super(tasks, ui, storage);
        this.description = description;
    }

    @Override
    public void execute() {
        ToDo todo = new ToDo(description);
        tasks.addTask(todo);
        ui.showTaskAdded(tasks);
    }
}
