package sleepy;

import sleepy.taskstorage.TaskList;
import sleepy.tools.Ui;

/**
 * This class is the main class for the Sleepy AI Chatbot.
 *
 * @author kjw142857
 */
public class Sleepy {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the Sleepy class.
     *
     * @param filePath Filepath of the file used to store the user's list.
     */
    public Sleepy(String filePath) {
        // Retrieve saved data
        taskList = new TaskList(filePath);
    }

    /**
     * Runs the Sleepy chatbot.
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
