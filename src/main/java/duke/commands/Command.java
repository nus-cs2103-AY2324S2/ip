package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.ui.Ui;

public class Command {
    protected String commandResponse;

    /**
     * Executes the command given by the input.
     * It is meant to be overridden by subclasses of `Command` to perform specific actions based on the input.
     * 
     * @param tasks ArrayList of tasks.
     * @param input the details of the task being executed.
     * @throws CommandException if there is some invalid input.
     * @throws IOException if there is some error in reading/writing to file.
     */
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        this.commandResponse = Ui.printOutput("I'm sorry, but I have zero idea what you're asking from me...");
    };

    /**
     * Returns the corresponding response to the command executed.
     *
     * @return Returns a String, containing the response.
     */
    public String getCommandResponse() {
        return this.commandResponse;
    }
}
