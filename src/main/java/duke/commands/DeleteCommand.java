package commands;

import java.io.IOException;
import java.util.ArrayList;
import storage.Storage;
import tasks.Task;
import ui.Ui;

public class DeleteCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (input.length < 2) {
            throw new CommandException(
                    "Please specify which task to delete. (format: delete <task no.>)");
        }

        Ui.printOutput("Noted. I've removed this task: ",
                tasks.get(Integer.parseInt(input[1]) - 1).toString(),
                "Now you have " + tasks.size() + " tasks in the list.");

        tasks.remove(Integer.parseInt(input[1]) - 1);

        Storage.writeToStorage(tasks);
    }

}
