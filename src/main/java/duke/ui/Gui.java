package duke.ui;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.TaskList;
import duke.ui.gui.Main;
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
     * Create a label with the response from the application to the user input
     *
     * @param input User input to be parsed
     *
     * @return String with the response from the application
     */
    public String getResponse(String input) throws DukeException {
        // Parse user input
        Command command = Parser.parse(input);
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
