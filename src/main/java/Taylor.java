import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.TaylorException;
import filestorage.Storage;
import parser.Parser;
import tasklist.DeleteTask;
import tasklist.FindTask;
import tasklist.InsertTask;
import tasklist.ListTask;
import tasklist.MarkTask;
import tasklist.SearchTask;
import tasks.Task;
import ui.Ui;

/**
 * Main class to execute Taylor ChatBot
 */
public class Taylor {
    public static void main(String[] args) {
        List<Task> tasksList = new ArrayList<>();
        // Load pre-existing task from Hard Disk
        try {
            tasksList = Storage.inputFromFile(tasksList);
        } catch (Exception e) {
            Ui.printError(e);
        }
        Ui.welcomeText();
        Scanner type = new Scanner(System.in);

        label:
        while (true) {
            String input = type.nextLine();

            if (input.isBlank()) {
                Ui.blankCommand();

            } else {
                String[] userInputSplit = input.split(" ", 2);
                String actionCalled = userInputSplit[0];

                Activity activity = getActivity(actionCalled);
                // Switch between different calls
                switch (activity) {
                    case BYE:
                        break label;

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
                // Save Task into File in Hard Disk
                try {
                    Storage.outputToFile(tasksList);
                } catch (Exception e) {
                    Ui.printError(e);
                }
            }
        }

        type.close();
        Ui.goodbyeText();
    }
}
