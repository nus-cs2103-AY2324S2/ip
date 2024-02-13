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

    /**
     * Creates new Dave object.
     * This is the chatbot Dave.
     * 
     * @param filepath File path to where tasks are stored for output.
     */
    public Dave(String filepath) {
        daveUi = new Ui();
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException exc) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot Dave.
     * Operations do not stop until the user has given the exit command, "bye".
     */
    public void run() {
        daveUi.sayHello();
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
     * The main program.
     * @param args
     */
    public static void main(String[] args) {
        new Dave("data/tasks.txt").run();
    }
}