import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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