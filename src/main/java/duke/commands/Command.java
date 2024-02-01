package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.ui.Ui;

public class Command {

    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        Ui.printOutput("I'm sorry, but I have zero idea what you're asking from me...");
    };
}
