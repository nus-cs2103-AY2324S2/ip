import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

/**
 * The Tam class is the main class from which Tam the Task Manager is launched.
 */
public class Tam {
    private static TaskList taskListObj;
    private static Scanner scannerObj = new Scanner(System.in);

    /**
     * Launches Tam and repeatedly waits for and reads input commands until a
     * termination command ('bye') is read
     *
     * @param args String arguments
     */
    public static void main(String[] args) {
        taskListObj = Storage.getSavedTasks();
        Ui.greet();
        String nextCommand = scannerObj.nextLine();
        int status = Parser.parseAndExecuteCommand(nextCommand, taskListObj);
        while (status == 1) {
            nextCommand = scannerObj.nextLine();
            status = Parser.parseAndExecuteCommand(nextCommand, taskListObj);
        }
        Ui.exit();
    }
}