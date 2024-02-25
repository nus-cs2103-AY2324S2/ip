package Duke;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;
import actions.Parser;
import actions.Ui;
import actions.TaskList;
import actions.DataManager;
public class Duke {

    /**
     * Duke is a command line interface application that helps to keep track of tasks
     */

    private static final String FILE_PATH = "./data/duke.txt";
    private TaskList taskList;
    private Ui ui;
    private DataManager dataManager;

    /**
     * instantiates a duke object
     * with TaskList, Ui and DataManager objects
     */
    public Duke() {
        this.dataManager = new DataManager(FILE_PATH);
        ArrayList<Task> tasks = dataManager.retrieveTasks();
        this.taskList = new TaskList(tasks);
        this.ui = new Ui();
        this.dataManager = new DataManager(FILE_PATH);
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Message");
        while (!isExit) {
            try {
                String message = input.nextLine();
                Parser parser = new Parser(message);
                ArrayList<Task> tasks = taskList.getTasks(); // Retrieve the current list of tasks
                dataManager.saveTasks(tasks);
                isExit = parser.parse(taskList, ui);
                if (isExit) {
                    ui.printOutro();
                }
            } catch (Exception e) {
                ui.printError("An error occured: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}