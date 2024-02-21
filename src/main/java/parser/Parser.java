package parser;

import java.util.List;

import exceptions.TaylorException;
import filestorage.Storage;
import taskhelper.WordsSplit;
import tasklist.DeleteTask;
import tasklist.FindTask;
import tasklist.InsertTask;
import tasklist.ListTask;
import tasklist.MarkTask;
import tasklist.SearchTask;
import tasks.Task;
import ui.Ui;

/**
 * Deals with making sense of the suer command
 */
public class Parser {
    /**
     * enums for different Commands
     */
    enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SEARCH, FIND, INVALID
    }
    /**
     * No constructor needed
     */
    private Parser() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Execute User Input command
     * @param input User Input
     * @param tasksList List of Tasks
     */
    public static String executeCommand(String input, List<List<Task>> tasksList) {
        String response = null;

        String[] userInputSplit = WordsSplit.separateWords(input, " ", true);
        String actionCalled = WordsSplit.getWord(userInputSplit, 0);
        Commands cmd = getCommands(actionCalled);

        // Switch between different calls
        switch (cmd) {
        case BYE:
            try {
                response = Storage.outputToFile(tasksList);
            } catch (Exception err) {
                response = Ui.printError(err);
            }
            break;
        case LIST:
            response = ListTask.execListTask(tasksList);
            break;
        case MARK:
        case UNMARK:
            try {
                response = MarkTask.execMarkTask(input, tasksList);
            } catch (TaylorException err) {
                response = Ui.printError(err);
            }

            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            try {
                response = InsertTask.execInsertTask(input, tasksList);
            } catch (TaylorException err) {
                response = Ui.printError(err);
            }
            break;
        case DELETE:
            try {
                response = DeleteTask.execDeleteTask(input, tasksList);
            } catch (TaylorException err) {
                response = Ui.printError(err);
            }
            break;
        case SEARCH:
            try {
                response = SearchTask.execSearchTask(input, tasksList);
            } catch (TaylorException err) {
                response = Ui.printError(err);
            }
            break;
        case FIND:
            try {
                response = FindTask.exec(input, tasksList);
            } catch (TaylorException err) {
                response = Ui.printError(err);
            }
            break;
        default:
            response = Ui.invalidCommand();
            break;
        }
        return response;
    }

    /**
     * Create enums for the different Commands
     * @param action User Input to select the different action to be taken
     * @return return Enums for that command
     */
    private static Commands getCommands(String action) {
        try {
            return Commands.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException err) {
            return Commands.INVALID;
        }
    }
}
