package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.StorageException;
import duke.parser.InputParser;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Duke class represents the chatbot application.
 */
public class Duke {
    private TaskList taskList;
    private InputParser inputParser;
    private Ui ui;
    private Storage storage;
    private boolean ready;

    /**
     * Constructs a Duke object and initializes its components.
     * If saved data is found, loads tasks from the storage; otherwise, initializes an empty task list.
     * If an error occurs while loading data, sets the application to an error state.
     */
    public Duke() {
        String filePath = "data/data.txt";
        this.ui = new Ui();
        this.inputParser = new InputParser();
        this.storage = new Storage(filePath);
        this.ready = true;

        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            this.ui.sendSystemMessage("There is no saved data in the system\n" + TextTemplate.LINE_BREAK);
            this.taskList = new TaskList();
        } catch (StorageException e) {
            this.ui.sendSystemMessage("Saved data is corrupted. Please delete/resolve file at: " + filePath);
            this.ready = false;
        }
    }

    private void exit() {
        this.ui.exit();
    }

    /**
     * Runs the Duke application, continuously accepting user input and processing commands until
     * the application is exited.
     * Handles user input, processes commands, and saves data to storage.
     */
    public void run() {
        while (this.ui.isActive()) {
            String input = this.ui.readNextLine();
            this.ui.sendSystemMessage(TextTemplate.LINE_BREAK);
            try {
                String response = inputParser.processCommand(input, this.taskList);
                this.ui.sendSystemMessage(response);
                this.storage.saveData(this.taskList);
                if (!this.inputParser.isActive()) {
                    this.ui.exit();
                }
            } catch (InvalidInputException e) {
                this.ui.sendSystemMessage(e.getMessage(), TextTemplate.LINE_BREAK);
            } catch (IOException e) {
                this.ui.sendSystemMessage("Error saving to file\nTerminating Process..." +
                        TextTemplate.LINE_BREAK);
                this.ui.exit();
            }
        }
    }

    /**
     * The main entry point for the Duke application.
     * Creates a Duke object and starts the application if it is ready.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        if (duke.ready) {
            duke.run();
        }
    }
}
