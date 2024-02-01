package parser;

import java.util.List;

import exceptions.TaylorException;
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
    public static void executeCommand(String input, List<Task> tasksList) {
        String[] userInputSplit = input.split(" ", 2);
        String actionCalled = userInputSplit[0];

        Commands cmd = getCommands(actionCalled);
        // Switch between different calls
        switch (cmd) {
        case BYE:
            throw new TaylorException("Quit");
        case LIST:
            ListTask.execListTask(tasksList);

            break;
        case MARK:
        case UNMARK:
            try {
                MarkTask.execMarkTask(input, tasksList);
            } catch (TaylorException err) {
                Ui.printError(err);
            }

            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            try {
                InsertTask.execInsertTask(input, tasksList);
            } catch (TaylorException err) {
                Ui.printError(err);
            }
            break;
        case DELETE:
            try {
                DeleteTask.execDeleteTask(input, tasksList);
            } catch (TaylorException err) {
                Ui.printError(err);
            }
            break;
        case SEARCH:
            try {
                SearchTask.execSearchTask(userInputSplit[1], tasksList);
            } catch (TaylorException err) {
                Ui.printError(err);
            }
            break;
        case FIND:
            try {
                FindTask.exec(userInputSplit[1], tasksList);
            } catch (TaylorException err) {
                Ui.printError(err);
            }
            break;
        default:
            Ui.invalidCommand();
            break;
        }
    }

    /**
     * enums for the different Commands
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
