import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Tam {
    private static TaskList taskListObj;

    public static void main(String[] args) {
        taskListObj = Storage.getSavedTasks();
        Ui.greet();
        int status = Parser.processCommand(taskListObj);
        while (status == 1) {
            status = Parser.processCommand(taskListObj);
        }
        Ui.exit();
    }
}