package command;

import task.Task;
import task.TaskList;
import dook.Ui;
import dook.Storage;
import java.util.ArrayList;

public class FindCommand extends Command {

    private final String target;

    public FindCommand(String target) {
        this.target = target;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> found = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).hasSubstring(target)) {
                found.add(tasks.get(i));
            }
        }
        if (found.size() == 0) {
            ui.println("No tasks foundd :(( meoww");
            return;
        }
        ui.println("Okay! here are the tasks I found! :3");
        for (int i = 0; i < found.size(); i++) {
            ui.println(String.format("%d. %s", i + 1, found.get(i).toString()));
        }
    }
}