package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exceptions.InvalidDateFormException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidMarkException;
import duke.exceptions.StorageException;
import duke.parser.InputParser;

/**
 * The Duke class represents the chatbot application.
 */
public class Duke {
    private TaskList taskList;
    private InputParser inputParser;
    private Ui ui;
    private Storage storage;
    private boolean isReady;

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
        this.isReady = true;

        try {
            this.taskList = new TaskList(storage.loadFile());
            this.taskList.updateTasks();
        } catch (FileNotFoundException e) {
            this.taskList = new TaskList();
        } catch (StorageException e) {
            this.taskList = new TaskList();
        }
    }


    /**
     * Runs the Duke application, continuously accepting user input and processing commands until
     * the application is exited.
     * Handles user input, processes commands, and saves data to storage.
     * This is only applicable for a text user interface.
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
            } catch (InvalidMarkException e) {
                this.ui.sendSystemMessage(e.getMessage(), TextTemplate.LINE_BREAK);
            } catch (InvalidInputException e) {
                this.ui.sendSystemMessage(e.getMessage(), TextTemplate.LINE_BREAK);
            } catch (InvalidDateFormException e) {
                this.ui.sendSystemMessage(e.getMessage(), TextTemplate.LINE_BREAK);
            } catch (IOException e) {
                this.ui.sendSystemMessage("Error saving to file\nTerminating Process..."
                        + TextTemplate.LINE_BREAK);
                this.ui.exit();
            }
        }
    }

    public String getResponse(String input) {
        try {
            String response = inputParser.processCommand(input, this.taskList);
            this.storage.saveData(this.taskList);
            if (response.equals("exit")) {
                System.exit(0);
            }
            return response;
        } catch (InvalidMarkException e) {
            return e.getMessage();
        } catch (InvalidInputException e) {
            return e.getMessage();
        } catch (InvalidDateFormException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        if (duke.isReady) {
            duke.run();
        }
    }
}
