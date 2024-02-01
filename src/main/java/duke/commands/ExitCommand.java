package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        Ui.printOutput("Goodbye my friend. See you soon!");
        System.exit(0);
    }
}
