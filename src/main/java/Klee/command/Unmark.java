package Klee.command;

import Klee.KleeException;
import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;
import Klee.task.Task;

/**
 * Represents the command for Klee to unmark a task as incomplete.
 */
public class Unmark extends Command {
    protected int index;

    /**
     * Constructor of Unmark class.
     *
     * @param index
     */
    public Unmark(int index) {
        this.index = index;
    }

    /**
     * Execute command to unmark a task.
     * Unmark task.
     * Invoke ui to show that task was unmarked.
     * Invoke storage to save tasks into a txt file.
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
            task.unMark();
            storage.saveTasks(tasks);
            ui.showMarked(task);
        } else {
            throw new KleeException("We do not have that many tasks on the list!");
        }
    }

    /**
     * Check if obj is equal to current instance.
     *
     * @param obj
     * @return If obj is instance of Unmark and has same index.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Unmark.class) {
            return this.index == ((Unmark) obj).index;
        } else {
            return false;
        }
    }
}
