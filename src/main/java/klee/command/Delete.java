package klee.command;

import klee.KleeException;
import klee.Storage;
import klee.TaskList;
import klee.Ui;
import klee.task.Task;

/**
 * Represents the command for Klee to delete a task.
 */
public class Delete extends Command {
    protected int index;

    /**
     * Constructor for the Delete class.
     *
     * @param index
     */
    public Delete(int index) {
        this.index = index;
    }

    /**
     * Execute the command to delete a task.
     * Check if the task exists, remove the task from tasks.
     * Invoke ui to let users know about deletion.
     * Invoke storage to save the current tasks into txt file.
     *
     * @param ui
     * @param storage
     * @param tasks
     * @throws KleeException
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) throws KleeException {
        if (index < tasks.size()) {
            Task task = tasks.get(index);
            tasks.remove(index);
            ui.showDeletion(task, tasks.size());
            storage.saveTasks(tasks);
        } else {
            throw new KleeException("But we only have " + tasks.size() + " tasks in the list...");
        }
    }

    /**
     * Return if the obj is an instance of Delete class with the same index.
     *
     * @param obj
     * @return If obj is Delete with same index.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Delete.class) {
            return this.index == ((Delete) obj).index;
        } else {
            return false;
        }
    }
}
