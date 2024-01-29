package Klee.command;

import Klee.KleeException;
import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;
import Klee.task.Task;

public class Unmark extends Command {
    protected int index;

    public Unmark(int index) {
        this.index = index;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Unmark.class) {
            return this.index == ((Unmark) obj).index;
        } else {
            return false;
        }
    }
}
