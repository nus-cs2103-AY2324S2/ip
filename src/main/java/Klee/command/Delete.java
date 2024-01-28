package Klee.command;

import Klee.KleeException;
import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;
import Klee.task.Task;

public class Delete extends Command {
    protected int index;

    public Delete (int index) {
        this.index = index;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Delete.class) return this.index == ((Delete) obj).index;
        else return false;
    }
}
