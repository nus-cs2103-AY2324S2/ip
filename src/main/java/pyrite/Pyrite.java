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
    /**
     * Constructs a Pyrite and loads the state from the file.
     */
    public Pyrite() {
        this.tasks = file.loadState(this.tasks);
    }
    /**
     * Gets the response from Pyrite.
     *
     * @param input Input from the user.
     * @return Response from Pyrite.
     */
    protected String getResponse(String input) {
        // Separate multiple commands by semicolon
        String[] commandStrings = input.split(";");
        String response = executeCommands(commandStrings);
        return response;
    }
    /**
     * Gets the name of Pyrite.
     *
     * @return Name of Pyrite.
     */
    protected String getName() {
        return NAME;
    }

    private String executeCommands(String ... commandStrings) throws PyriteException {
        String response = "";
        for (int i = 0; i < commandStrings.length; i++) {
            try {
                response += executeCommand(commandStrings[i]);
            } catch (PyriteException e) {
                response += e.toString();
                response += "\n\nThere are "
                        + (commandStrings.length - i - 1)
                        + " more commands that were not executed:";
                for (int j = i + 1; j < commandStrings.length; j++) {
                    response += "\n" + commandStrings[j];
                }
                throw new PyriteException(response);
            }
            if (i != commandStrings.length - 1) {
                response += "\n\n";
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
