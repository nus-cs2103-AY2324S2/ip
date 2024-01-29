package Klee.command;

import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;
import Klee.task.Task;

public class ToDo extends Command {
    protected String description;
    public ToDo(String description) {
        this.description = description;
    }

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        Task task = new Klee.task.ToDo(description);
        tasks.add(task);
        ui.showCreation(task, tasks.size());
        storage.saveTasks(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == ToDo.class) {
            return this.description.equals(((ToDo) obj).description);
        } else {
            return false;
        }
    }
}