package aaron;

import java.io.FileNotFoundException;

import aaron.command.Command;
import aaron.parser.Parser;
import aaron.storage.Storage;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Represents the main AaronBot object
 * 
 */
public class AaronBot {
    public static final String TASKLIST_FILEPATH = "data/TaskList.txt";
    private TaskList taskList;
    private UI ui;
    String greetingString;

    public AaronBot() {
        taskList = new TaskList();
        ui = new UI();
        greetingString = ui.greet();
        try {
            taskList = new TaskList(Storage.loadFromFile(TASKLIST_FILEPATH));
            greetingString += "\n" + ui.taskListLoadedMessage(taskList, ui);
        } catch (FileNotFoundException e) {
            greetingString += "\n" + ui.errorMessage(e);
        }
        ui.greet();
    }

    /**
     * Method that gets the response that corresponds to a user input
     * @param userInput user input
     * @return
     */
    public String getResponse(String userInput) {
        try {
            Command cmd = Parser.parse(userInput, taskList, ui);
            String result = cmd.run(taskList, ui);
            Storage.writeToFile(TASKLIST_FILEPATH, taskList);
            return result;
        } catch (Exception e) {
            return ui.errorMessage(e);
        }
    }
    /**
     * method that returns initial greeting strings
     * @return greeting
     */
    public String startChat() {
        return greetingString;
    }
}