package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Todo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.printAddSuccessMessage(todo, tasks.size());
        storage.saveToFile(tasks.getTasks());
    }
}

}
