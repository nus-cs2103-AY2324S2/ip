package commands;

import java.io.IOException;
import java.util.ArrayList;
import tasks.Task;
import ui.Ui;

public class ExitCommand extends Command {
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        Ui.printOutput("Goodbye my friend. See you soon!");
        System.exit(0);
    }
}
