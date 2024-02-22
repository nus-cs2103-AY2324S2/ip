package ciara.ui;

import ciara.commands.Command;
import ciara.exceptions.CiaraException;
import ciara.parser.Parser;
import ciara.storage.TaskList;
import ciara.ui.gui.Main;
import javafx.application.Application;

/**
 * The UI GUI class handles the displaying of UI elements in the application
 * using a graphical user interface
 *
 * @author Ryan NgWH
 */
public class Gui extends Ui {
    /**
     * Tasklist for the application
     */
    private static TaskList taskList;

    /**
     * Gets the command associated with the user input
     *
     * @param input User input to be parsed
     *
     * @return Relevant command for the user input
     */
    public Command getCommand(String input) throws CiaraException {
        // Parse user input
        return Parser.parse(input);
    }

    /**
     * Gets the response from a command
     *
     * @param command Command to be executed
     *
     * @return String with the response from the application
     */
    public String getResponse(Command command) throws CiaraException {
        assert command != null; // Command should exist

        // Execute command
        return command.execute(Gui.taskList);
    }

    /**
     * Displays the UI of the application
     *
     * @param taskList Tasklist to use for the application
     * @param args     Arguments for the application
     */
    @Override
    public void startUI(TaskList taskList, String[] args) {
        Gui.taskList = taskList;
        Application.launch(Main.class, args);
    }
}
