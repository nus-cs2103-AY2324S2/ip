package unim.application;
import unim.io.Storage;
import unim.io.Ui;

/**
 * The Unim class represents the main application that manages user interactions
 * for a task management system.
 */
public class Unim {
    private TaskList taskList;

    /**
     * Creates a new instance of the Unim application.
     * Initializes the task list, loads existing tasks from storage, and creates the necessary folders.
     */
    public Unim() {
        this.taskList = new TaskList();
        Storage.loadFile(taskList.getTaskList());
        Storage.createFolder();
    }

    /**
     * Gets the response from the Unim application based on user input.
     *
     * @param input The user input to be processed.
     * @return The response generated by the application.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.showWelcomeMessage();

        String unimResponse = parser.handleCommand(input, taskList);
        return unimResponse;
    }

}
