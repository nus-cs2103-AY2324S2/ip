package duke.core;

import duke.commands.Command;
import duke.exceptions.MeanDukeException;
import duke.tasks.TaskList;

/**
 * This class represents a MEAN chatbot with task-tracking capabilities
 */
public class MeanDuke {

    //Creates an empty task list
    private static TaskList taskList = new TaskList();

    public static String initialise(MainWindow controller) {
        taskList = Storage.load(controller);
        return "What do you want this time?";
    }

    public static String getResponse(String input, MainWindow controller) {
        try {
            Command cmd = Parser.parseUserInput(input, taskList);
            String ret = cmd.execute();
            Storage.save(taskList, controller);
            if (cmd.isExitCommand()){
                javafx.application.Platform.exit();
            }
            return ret;
        } catch (MeanDukeException e) {
            return e.getMessage();
        }
    }
}
