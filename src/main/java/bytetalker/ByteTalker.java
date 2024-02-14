package bytetalker;

import bytetalker.task.TaskList;
import bytetalker.ui.Ui;
import bytetalker.storage.Storage;
import bytetalker.parser.Parser;

import java.io.FileNotFoundException;

/**
 * The ByteTalker program implements a chatbot where it processes user input.
 *
 * @author Junseo Kim
 * @version 0.1
 * @since 2024-01-28
 */
public class ByteTalker {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    boolean isExit = false;

    public ByteTalker(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.setupDirectoryAndFile();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundErrorMsg();
            isExit = true;
        }
    }

    /**
     * Runs the chatbot and allows user input. User input is processed with other methods.
     */
    public void run() {
        ui.showWelcome();
        while (!isExit) {
            String userInputString = ui.storeUserInput();
            String[] splitMessages = Parser.parse(userInputString);
            if (userInputString.equals("bye")) {
                break;
            } else if (userInputString.equals("list")) {
                this.ui.returnList(this.tasks.getTasks());
            } else if (splitMessages[0].equals("mark")) {
                this.tasks.markTask(splitMessages, storage, ui);
            } else if (splitMessages[0].equals("unmark")) {
                this.tasks.unmarkTask(splitMessages, storage, ui);
            } else if (splitMessages[0].equals("delete")) {
                this.tasks.deleteTask(Integer.parseInt(splitMessages[1]), storage, ui);
            } else if (splitMessages[0].equals("find")) {
                this.tasks.findTask(splitMessages, ui);
            } else {
                this.tasks.addTask(splitMessages, storage, ui);
            }
        }
        ui.showBye();
    }

    /**
     * This is the main method which starts the chatbot by running run method.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new ByteTalker("./data/bytetalker.ByteTalker.txt").run();
    }
}
