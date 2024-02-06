import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Tam {
    private static TaskList taskListObj;
    private static Scanner scannerObj = new Scanner(System.in);

    public static void main(String[] args) {
        taskListObj = Storage.getSavedTasks();
        Ui.greet();
        String nextCommand = scannerObj.nextLine();
        int status = Parser.processCommand(nextCommand, taskListObj);
        while (status == 1) {
            nextCommand = scannerObj.nextLine();
            status = Parser.processCommand(nextCommand, taskListObj);
        }
        Ui.exit();
    }
}