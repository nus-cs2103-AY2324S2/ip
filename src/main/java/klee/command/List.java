package klee.command;

import klee.Storage;
import klee.TaskList;
import klee.Ui;

/**
 * Represents the command for Klee to list all tasks.
 */
public class List extends Command {
    /**
     * Execute command to list all tasks.
     * Invoke ui to show all tasks.
     *
     * @param ui
     * @param storage
     * @param tasks
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        ui.showTasks(tasks);
    }

    /**
     * Check if obj is instance of class List.
     *
     * @param obj
     * @return If obj is instance of List.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == List.class;
    }
}
