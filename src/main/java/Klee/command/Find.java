package Klee.command;

import Klee.Storage;
import Klee.TaskList;
import Klee.Ui;

import Klee.task.Task;

public class Find extends Command {
    String searchTerm;
    public Find(String searchTerm) {
        this.searchTerm = searchTerm;
    }

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

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Bye.class;
    }
}
