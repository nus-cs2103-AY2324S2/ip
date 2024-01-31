package nollid.commands;

import java.util.ArrayList;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;
import nollid.tasks.Todo;

/**
 * TodoCommand class represents a command to add a new ToDo task.
 */
public class TodoCommand extends Command {
    private final ArrayList<String> argsList;

    public TodoCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        if (argsList.size() == 1) {
            throw new InvalidArgumentException("Todo description cannot be empty!\n"
                    + "Usage: todo [task description]");
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < argsList.size(); i++) {
            if (i != argsList.size() - 1) {
                taskDescription.append(argsList.get(i)).append(" ");
            } else {
                taskDescription.append(argsList.get(i));
            }
        }

        Todo task = new Todo(taskDescription.toString());

        tasks.add(task);

        String message = "Alright, added:\n" + "\t" + task + "\n";
        message += tasks.summary();
        ui.sendMessage(message);
        storage.update(tasks);
    }
}
