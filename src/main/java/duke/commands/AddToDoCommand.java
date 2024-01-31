package commands;

import java.io.IOException;
import java.util.ArrayList;
import storage.Storage;
import tasks.Task;
import tasks.ToDo;
import ui.Ui;

public class AddToDoCommand extends Command {

    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        if (input.length < 2) {
            throw new CommandException(
                    "Please add the task description. (format: todo <task description>)");
        }

        ToDo todoTask = new ToDo(input[1]);
        tasks.add(todoTask);

        Storage.writeToStorage(todoTask);

        Ui.printOutput("Got it. I've added this task:" + todoTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

}
