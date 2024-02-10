package commands;

import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import java.time.LocalDateTime;

public class createTodoCommand extends Command{
    private String description;

    public createTodoCommand(String description) {
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
