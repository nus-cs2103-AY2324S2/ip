package duke.commands;

import java.io.IOException;
import java.util.ArrayList;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (input.length < 2) {
            throw new CommandException(
                    "Please specify which task to delete. (format: delete <task no.>)");
        }

        if (Integer.parseInt(input[1]) > tasks.size()) {
            throw new CommandException("The tasks you indicated does not exist");
        }
        
        Ui.printOutput("Noted. I've removed this task: ",
                tasks.get(Integer.parseInt(input[1]) - 1).toString(),
                "Now you have " + tasks.size() + " tasks in the list.");

        tasks.remove(Integer.parseInt(input[1]) - 1);

        Storage.writeToStorage(tasks);
    }

}
