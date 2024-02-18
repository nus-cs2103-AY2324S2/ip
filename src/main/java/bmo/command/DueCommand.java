package bmo.command;

import bmo.util.Storage;
import bmo.util.TaskList;
import bmo.ui.Ui;
import bmo.task.Task;
import bmo.task.Deadlines;


import java.time.LocalDateTime;

public class DueCommand extends Command {

    private final String desc;
    private final LocalDateTime by;

    public DueCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadlines(this.desc, this.by);
        tasks.add(newTask);
        ui.printAddTask(newTask, tasks.size());
    }
}
