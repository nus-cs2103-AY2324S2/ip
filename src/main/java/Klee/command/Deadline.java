package Klee.command;

import Klee.Ui;
import Klee.Storage;
import Klee.TaskList;
import Klee.task.Task;

import java.time.LocalDateTime;

public class Deadline extends Command {
    protected String description;
    protected LocalDateTime deadline;
    public Deadline (String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        Task task = new Klee.task.Deadline(description, deadline);
        tasks.add(task);
        ui.showCreation(task, tasks.size());
        storage.saveTasks(tasks);
    }
}