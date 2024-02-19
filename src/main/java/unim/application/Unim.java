package unim.application;
import unim.io.Storage;
import unim.io.Ui;

/**
 * The Unim class represents the main application that manages user interactions
 * for a task management system.
 */
public class Unim {

    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        Ui ui = new Ui();
        ui.showWelcomeMessage();
        TaskList taskList = new TaskList();
        Parser parser = new Parser();
        Storage.createFolder();
        Storage.loadFile(taskList.getTaskList());
        String unimResponse = parser.handleCommand(input, taskList);
        return unimResponse;
    }

}
