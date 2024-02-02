package dave;

import java.io.IOException;

import dave.exceptions.ChatbotException;
import dave.commands.Command;

public class Dave {
    /** The output file. */
    private Storage storage;
    /** The task list to record tasks. */
    private TaskList taskList;
    /** The UI to print feedback to user. */
    private Ui dave;

    /**
     * Creates new Dave object.
     * This is the chatbot Dave.
     * 
     * @param filepath File path to where tasks are stored for output.
     */
    public Dave(String filepath) {
        dave = new Ui();
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
        dave.greet();
        boolean isExit = false;
        if (taskList.getNumberOfTasks() != 0) {
            dave.showLoadingSuccess(taskList.getNumberOfTasks());
        } else {
            dave.showLoadingError();
        }
        while (!isExit) {
            try {
                String fullCommand = dave.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, dave, storage);
                isExit = c.isExit();
            } catch (ChatbotException exc) {
                dave.showError(exc.getMessage());
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