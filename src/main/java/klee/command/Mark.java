package klee.command;

import klee.KleeException;
import klee.Storage;
import klee.TaskList;
import klee.Ui;
import klee.task.Task;

/**
 * Represents the command for Klee to mark a task as complete.
 */
public class Mark extends Command {
    protected int index;

    /**
     * Constructor for Mark class.
     *
     * @param index
     */
    public Mark(int index) {
        this.index = index;
    }

    /**
     * Execute command to mark a task.
     * Marks the task.
     * Invoke ui to show that task was marked.
     * Invoke storage to save tasks into txt file.
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
            task.mark();
            storage.saveTasks(tasks);
            ui.showMarked(task);
        } else {
            throw new KleeException("We do not have that many tasks on the list!");
        }
    }

    /**
     * Check if obj is instance of Mark class with same fields.
     *
     * @param obj
     * @return If obj belongs to Mark class with same index.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Mark.class) {
            return this.index == ((Mark) obj).index;
        } else {
            return false;
        }
    }
}
