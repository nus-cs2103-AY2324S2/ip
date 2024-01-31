package duke;

import java.io.IOException;
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    private static final String FILE_PATH = "./data/taskList.txt";

    /**
     * Constructor for Lunaris chatbot.
     *
     * @param filePath for locating filepath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList((storage.loadTasksFromFile()));
        } catch (DukeException e) {
            ui.loadErrorMessage();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.welcomeMessage();
        while (true) {
            try {
                String input = ui.readCommand();
                if (input.equalsIgnoreCase("bye")) {
                    break;
                }
                Parser.parseCommand(input, taskList, ui);
                storage.saveTasksToFile(taskList.getTasks());
            } catch (IOException | DukeException e) {
                System.out.println("Sorry, Error occurred! Shutting down...");
                break;
            }
        }
        ui.goodByeMessage();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}