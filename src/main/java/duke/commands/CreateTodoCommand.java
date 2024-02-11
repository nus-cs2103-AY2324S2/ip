package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class CreateTodoCommand extends Command{
    private String description;

    public CreateTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new ToDo(description);
        tasks.addTask(newTask);
        ui.showLine();
        newTask.displayTask(tasks.size());
        ui.showLine();
        return true;
    }
}
