package aaron.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import aaron.exception.ParsingException;
import aaron.parser.Parser;
import aaron.storage.Storage;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Represents the main AaronBot class, executes the user-bot interaction task
 * adding/deleting/marking
 */
public class AaronBot {
    public static final String TASKLIST_FILEPATH = "data/TaskList.txt";
    
    /**
     * Method to begin the execute cycle
     */
    public static void execute() {
        TaskList taskList = new TaskList();
        boolean isBye = false;
        UI ui = new UI();
        try {
            taskList = new TaskList(Storage.loadFromFile(TASKLIST_FILEPATH));
            ui.taskListLoadedMessage(taskList, ui);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        ui.greet();

        while (!isBye) {
            String userCommand = ui.readCommand();
            try {
                isBye = Parser.parse(userCommand, taskList, ui);
            } catch (ParsingException e) {
                ui.showParseError(userCommand);
            }
        }
        ui.closeScanner();
        try {
            Storage.writeToFile(TASKLIST_FILEPATH, taskList);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        execute();
    }
}