package ui;

import javafx.application.Platform;
import parser.ParseExecutionable;
import parser.Parser;
import task.ActionTask;
import task.TaskStorage;
import util.DataReader;
import util.DataWriter;
import util.TextUi;

/**
 * This is a main user interface for the "ChatBot", promptly named BobBot.
 *
 * This class is a front to manage user input, displaying the corresponding output,
 * and managed the conditional statements for the prompting.
**/
public class BobBot {
    /**
     * This method will handle user input by parsering the input
     * and creating an Action object for it to be executed.
     * When executed, it will return the output of the action.
     *
     * @param input the user input.
     */
    public String getResponse(String input) {
        Parser parse = new Parser();
        TextUi textUi = new TextUi();
        DataReader dataReader = new DataReader();
        TaskStorage taskStorage = dataReader.readDataFile(textUi);
        ParseExecutionable actionable = parse.parseInput(input);
        String botResponse = actionable.execute(taskStorage);
        DataWriter dataWriter = new DataWriter();
        dataWriter.saveData(taskStorage);
        if (actionable instanceof ActionTask) {
            ActionTask actionTask = (ActionTask) actionable;
            if (actionTask.isItExitAction()) {
                Platform.exit();
                return botResponse;
            }
        }
        return botResponse;
    }
}
