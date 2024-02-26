package dave;

import java.io.IOException;

import dave.commands.Command;
import dave.exceptions.ChatbotException;

public class Dave {
    /** The output file. */
    private Storage storage;
    /** The task list to record tasks. */
    private TaskList taskList;
    /** The UI to print feedback to user. */
    private Ui daveUi;
    /** The filepath where task(s) are saved. */
    private static final String SAVE_FILE = "data/tasks.txt";

    /**
     * The constructor for Dave chatbot.
     * This is the chatbot Dave.
     * 
     */
    public Dave() {
        daveUi = new Ui();
        storage = new Storage(SAVE_FILE);
        try {
            taskList = new TaskList(storage.load());
            assert taskList.getNumberOfTasks() != 0;
        } catch (IOException exc) {
            taskList = new TaskList();
            assert taskList.getNumberOfTasks() == 0;
        }
    }

    public String getLoadResult() {
        if (taskList.getNumberOfTasks() != 0) {
            return daveUi.showLoadingSuccess(taskList.getNumberOfTasks());
        } else {
            return daveUi.showLoadingError();
        }
    }

    /**
     * Runs the chatbot Dave.
     * Operations do not stop until the user has given the exit command, "bye".
     */
    public void run() {
        boolean isExit = false;
        if (taskList.getNumberOfTasks() != 0) {
            daveUi.showLoadingSuccess(taskList.getNumberOfTasks());
        } else {
            daveUi.showLoadingError();
        }
        while (!isExit) {
            try {
                String fullCommand = daveUi.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, daveUi, storage);
                isExit = c.isExit();
            } catch (ChatbotException exc) {
                daveUi.showError(exc.getMessage());
            }
        }
    }

    /**
     * Get responses from the Chatbot Dave.
     * Operations do not stop until the user has given the exit command, "bye".
     * 
     * @param input The user input.
     * @return The message to show to the user.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, daveUi, storage);
        } catch (ChatbotException exc) {
            return daveUi.showError(exc.getMessage());
        }
    }

}