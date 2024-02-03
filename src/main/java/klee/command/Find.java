package klee.command;

import klee.Storage;
import klee.TaskList;
import klee.Ui;
import klee.task.Task;

/**
 * Represent the command to filter tasks by their description.
 */
public class Find extends Command {
    protected String searchTerm;

    /**
     * Constructor for Find class.
     *
     * @param searchTerm
     */
    public Find(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * With the search term given, filter all tasks to check if they contain the search term.
     * Invoke ui to display all filtered tasks.
     *
     * @param ui
     * @param storage
     * @param tasks
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        TaskList filteredTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.include(searchTerm)) {
                filteredTasks.add(task);
            }
        }
        ui.showFilteredTasks(filteredTasks);
    }

    /**
     * Check if obj is instance of Find with same fields.
     *
     * @param obj
     * @return If obj is equal to this.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Find.class;
    }
}
