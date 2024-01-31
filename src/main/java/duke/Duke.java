package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.CommandResult;
import duke.common.DataStorage;
import duke.parser.Parser;
import duke.ui.Ui;

public class Duke {

    private Ui ui;
    private DataStorage dataStorage;

    public Duke() {
        // Grade

    }

    public void start() {
        // TODO: There could be a try catch here
        this.ui = new Ui();
        this.dataStorage = new DataStorage(Integer.MAX_VALUE, "database.txt");
        ui.showWelcome();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbye();
        System.exit(0);
    }


    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


    public CommandResult executeCommand(Command command) {
        command.setData(dataStorage);
        CommandResult commandResult = command.execute();
        return commandResult;
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {

        Command command;

        do {
            // Keep reading user input until they type "bye"
            String userInput = ui.readCommand();
            command = new Parser().parseCommand(userInput);

            CommandResult commandResult = executeCommand(command);

            ui.showLine();
            System.out.println(commandResult.feedbackToUser);
            ui.showLine();

        } while (!ByeCommand.isExit(command));
    }


}

