package tommy;

import tommy.command.Command;
import tommy.task.TaskList;
import tommy.exception.TommyException;

/**
 * Represents the Chatbot.
 */
public class Tommy {

    private Storage storage;
    private TaskList tasks;

    // deals with interactions with the user
    private Ui ui;

    /**
     * Constructor for the Chatbot and retrieve the past log from the storage.
     *
     * @param filePath String of the path of the file to retrieve the past log taskList from.
     */
    public Tommy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TommyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Chatbot and takes in input while it is active. Terminates when input
     * command is BYE
     */
    public void run() {
        boolean isActive = true;
        ui.greet();

        while (isActive) {
            try {
                String userInput = this.ui.getInput();
                Command command = Parser.parseCommand(userInput);
                command.execute(this.storage, this.tasks, this.ui);
                isActive = command.isActive();
            } catch (TommyException e) {
                Ui.printError(e);
            }
        }

    }

    public static void main(String[] args) {
        new Tommy("./data/tasks.txt").run();
    }
}

