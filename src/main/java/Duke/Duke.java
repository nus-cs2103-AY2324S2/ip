package Duke;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        UI.showLogo();
        UI.showAvailCommands();
        TaskList storage = new TaskList();
        Path filePath = Paths.get("./data/duke.txt");
        Path writeToFile = FileHandler.handleFile(storage, filePath);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                UI.showExitMsg();
                break;
            }
            if (input.equals("check dates")) {
                UI.checkDatesMsg();
                String fromDate = scan.nextLine();
                System.out.println("End: ");
                String toDate = scan.nextLine();
                ArrayList<Task> taskList = TaskHandler.checkSchedule(fromDate, toDate, storage);
                UI.scheduledTaskMsg();
                for (Task task : taskList) {
                    System.out.println(task.toString());
                }
            } else {
                TaskHandler.doTasks(input, storage, writeToFile);
            }
            input = scan.nextLine();

        }

    }
}
