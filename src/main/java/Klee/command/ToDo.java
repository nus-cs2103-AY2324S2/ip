package Klee.command;

import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;
import Klee.task.Task;

/**
 * Represents command for Klee to create new ToDo and add it to tasks.
 */
public class ToDo extends Command {
    protected String description;

    /**
     * Constructor for ToDo class.
     *
     * @param description
     */
    public ToDo (String description) {
        this.description = description;
    }

    /**
     * Execute command to create new ToDo and add it to tasks.
     * Create new instance of ToDo class and add it to tasks.
     * Invoke ui to show that task was added.
     * Invoke storage to save tasks into a txt file.
     *
     * @param ui
     * @param storage
     * @param tasks
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        Task task = new Klee.task.ToDo(description);
        tasks.add(task);
        ui.showCreation(task, tasks.size());
        storage.saveTasks(tasks);
    }

    /**
     * Check if obj is instance of ToDo class with same fields.
     *
     * @param obj
     * @return If obj is instance of ToDo with same fields.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == ToDo.class) return this.description.equals(((ToDo) obj).description);
        else return false;
    }
}