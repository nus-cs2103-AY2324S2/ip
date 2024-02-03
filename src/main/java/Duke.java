/**
 * The main user interface for the "ChatBot", promptly named BobBot.
 * <p>
 * This class is a front to manage user input, displaying the corresponding output, 
 * and managed the conditional statements for the prompting.
 */
import parser.ParseExecutionable;
import parser.Parser;
import task.ActionTask;
import task.TaskStorage;
import util.DataReader;
import util.DataWriter;
import util.Messages;
import util.TextUi;

public class Duke {
    public static void main(String[] args) {
        TextUi textUi = new TextUi();
        Parser parser = new Parser();
        textUi.printMessage(Messages.MESSAGE_START_BOT);
        TaskStorage ts = new DataReader().readDataFile(textUi);
        ParseExecutionable actionable = null;
        Boolean isExit = false;
        do {
            String userInput = textUi.readNextLine();
            actionable = parser.parseInput(userInput);
            textUi.printMessage(actionable.execute(ts));
            if (actionable instanceof ActionTask) {
                ActionTask actionTask = (ActionTask) actionable;
                isExit = actionTask.isExit;
            }
        } while (!isExit);
        DataWriter dataWriter = new DataWriter();
        dataWriter.saveData(ts);
        textUi.closeReader();
    }
}