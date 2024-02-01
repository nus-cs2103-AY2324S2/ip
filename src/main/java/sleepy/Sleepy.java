package sleepy;

import sleepy.taskstorage.TaskList;
import sleepy.tools.Ui;

import java.util.Scanner;

/**
 * This class is the main class for the Sleepy AI Chatbot.
 *
 * @author kjw142857
 */
public class Sleepy {
    private TaskList taskList;
    private Ui ui;

    public Sleepy(String filePath) {
        // Retrieve saved data
        taskList = new TaskList(filePath);
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        // Initialise chatbot
        ui = new Ui();
        // UI starts accepting commands on the task list
        ui.acceptCommands(taskList);
    }

    public static void main(String[] args) {
        new Sleepy("./src/main/java/sleepy/taskstorage/HardDiskStorage.txt").run();
    }
}
