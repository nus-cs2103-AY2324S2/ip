package yoda;

import yoda.parser.Parser;
import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.constants.FilePaths;
import yoda.ui.YodaUI;

import java.io.IOException;

public class Yoda {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private YodaUI yodaUI;

    private String filePath = FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;

    public Yoda() {
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTasks();
        } catch (IOException e) {
            this.tasks = new TaskList();
        }
        this.yodaUI = new YodaUI(tasks, storage); // Initialize before passing to Parser
        this.parser = new Parser(yodaUI);
    }

    /**
     * Gets the greeting message to be displayed in the GUI.
     * @return Greeting message.
     */
    public String getGreeting() {
        return yodaUI.printGreeting();
    }


    /**
     * Generates a response to user input based on the command parsed.
     * @param input User input string.
     * @return Response to be displayed in the GUI.
     */
    public String getResponse(String input) {
        try {
            String response = parser.parseAndExecute(input);
            storage.saveTasks(tasks);
            return response;
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
