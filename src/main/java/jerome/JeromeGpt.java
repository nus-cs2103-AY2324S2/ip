package jerome;

import jerome.commands.ByeCommand;
import jerome.commands.Command;
import jerome.commands.CommandResult;
import jerome.common.DataStorage;
import jerome.parser.Parser;
import jerome.ui.Ui;

/**
 * Runs the JeromeGPT, a command-line application that allows user interaction by entering commands.
 * This class is used for testing the command line interface.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * program design.
 */
public class JeromeGpt {

    private Ui ui;
    private DataStorage dataStorage;

    /**
     * Duke is a command-line application that allows users to interact with it by entering commands.
     */
    public JeromeGpt() { }

    /**
     * Starts the Duke application by initializing the user interface and data storage,
     * and displaying the welcome message.
     */
    public void start() {
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

    /**
     * Runs the Duke application by starting it, running the command loop until the bye command is issued.
     */

    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Executes the main method of the application for command-line mode.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        new JeromeGpt().run();
    }


    /**
     * Executes the given command and returns the result.
     *
     * @param command the command to be executed
     * @return the result of the command execution
     */
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

            // Check that userInput is not null.
            assert(userInput != null);

            command = new Parser().parseCommand(userInput);

            CommandResult commandResult = executeCommand(command);

            ui.showLine();
            System.out.println(commandResult.feedbackToUser);
            ui.showLine();

        } while (!ByeCommand.isExit(command));
    }


    /**
     * Retrieves the response from the application based on the user input.
     *
     * @param input the user input.
     * @return the response generated required by Duke.
     */
    public String getResponse(String input) {
        Command command = new Parser().parseCommand(input);
        CommandResult commandResult = executeCommand(command);
        return commandResult.feedbackToUser;
    }


}

