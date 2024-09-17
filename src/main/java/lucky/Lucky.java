package lucky;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import lucky.commands.Command;
import lucky.commands.CommandException;
import lucky.commands.ParseCommand;
import lucky.storage.Storage;
import lucky.tasks.Task;
import lucky.ui.Ui;

public class Lucky {
    private static Ui ui;
    private static ArrayList<Task> tasks;

    public Lucky() throws FileNotFoundException {
        ui = new Ui();
        tasks = Storage.init();
    }

    /**
     * Returns a response using the provided input.
     *
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
