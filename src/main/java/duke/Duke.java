package duke;

import duke.exceptions.InvalidInputException;
import duke.exceptions.StorageException;
import duke.parser.InputParser;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private TaskList taskList;
    private InputParser inputParser;
    private Ui ui;
    private Storage storage;
    private boolean ready;

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
                this.ui.sendSystemMessage("Error saving to file\nTerminating Process..." + TextTemplate.LINE_BREAK);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        if (duke.ready) {
            duke.run();
        }
    }
}
