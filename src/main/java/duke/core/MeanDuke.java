package duke.core;

import duke.commands.Command;
import duke.exceptions.MeanDukeException;
import duke.gui.MainWindow;
import duke.tasks.TaskList;

/**
 * This class represents a MEAN chatbot with task-tracking capabilities
 */
public class MeanDuke {

    //Creates an empty task list
    private static TaskList taskList = new TaskList();

    /**
     * Initialises the MeanDuke chatbot
     *
     * @param controller The controller that will be controlling the graphical outputs of the chatbot
     * @return String containing the introductory message of the chatbot
     */
    public static String initialise(MainWindow controller) {
        taskList = Storage.load(controller);
        return "What do you want this time?";
    }

    /**
     * Gets a response from MeanDuke given an input by the user
     *
     * @param input The string input by the user
     * @param controller The controller controlling the graphical outputs of the chatbot
     * @return String containing the response from MeanDuke
     */
    public static String getResponse(String input, MainWindow controller) {
        try {
            Command cmd = Parser.parseUserInput(input, taskList);
            String ret = cmd.execute();
            Storage.save(taskList, controller);
            if (cmd.isExitCommand()) {
                javafx.application.Platform.exit();
            }
            return ret;
        } catch (MeanDukeException e) {
            return e.getMessage();
        }
    }
}
