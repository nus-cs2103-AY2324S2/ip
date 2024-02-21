package pyrite;

import pyrite.command.Command;
import pyrite.command.ExitCommand;

/**
 * The Pyrite chatbot.
 */
public class Pyrite {
    private static final String NAME = "Pyrite";

    private TaskList tasks = new TaskList();
    private StateFile file = new StateFile();
    public Pyrite() {
        this.tasks = file.loadState(this.tasks);
    }

    protected String getResponse(String input) {
        // Separate multiple commands by semicolon
        String[] commandStrings = input.split(";");
        String response = executeCommands(commandStrings);
        return response;
    }

    protected String getName() {
        return NAME;
    }

    private String executeCommands(String ... commandStrings) {
        String response = "";
        for (int i = 0; i < commandStrings.length; i++) {
            response += executeCommand(commandStrings[i]);
            if (i != commandStrings.length - 1) {
                response += "\n";
            }
        }
        return response;
    }

    private String executeCommand(String commandString) {
        commandString = commandString.trim(); // remove leading and trailing whitespaces
        Command command = Parser.parse(commandString);
        if (command instanceof ExitCommand) {
            //exit program
            System.exit(0);
        }
        String response = command.execute(this.tasks, this.file);

        return response;
    }
}
