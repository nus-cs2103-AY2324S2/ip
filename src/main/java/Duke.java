import task.TaskList;
import utilities.Ui;
import command.Command;
import exceptions.DukeException;
import utilities.Storage;
import utilities.Parser;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Duke class constructor.
     * @param fileName Name of the file that the data is going to be saved into.
     * @param directoryName Name of the directory that the file is going to be saved into.
     */
    public Duke(String fileName, String directoryName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName, directoryName);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * Starts the TodoPal chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parse(userInput);
                command.execute(taskList, storage, ui);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main function that is called when the program is initially booted up.
     * @param args Command line user input.
     */
    public static void main(String[] args) {
        new Duke("duke.txt", "./src/main/data").run();
    }
}
