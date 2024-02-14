package duke;

import duke.commands.Command;
import duke.commands.CommandException;
import duke.commands.ParseCommand;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    private static Ui ui;
    private static ArrayList<Task> tasks;

    public Duke() throws FileNotFoundException {
        ui = new Ui();
        tasks = Storage.init();
    }

    /**
     * Returns a response using the provided input.
     * @param input String input.
     * @return String output of the command executed.
     */
    public String getResponse(String input) {
        String[] strArrCommand = ui.readCommand(input);
        String response;
        try {
            Command command = ParseCommand.parse(strArrCommand);
            command.execute(tasks, strArrCommand);
            response = command.getCommandResponse();
        } catch (CommandException | IOException e) {
            return Ui.printOutput(e.getMessage());
        }
        return response;
    }
}
