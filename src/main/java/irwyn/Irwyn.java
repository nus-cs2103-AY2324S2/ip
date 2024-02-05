package irwyn;

import commands.Command;
import commands.CommandParser;
import irwyn.exceptions.IrwynException;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

import java.util.Scanner;

/**
 * This class encapsulates Irwyn chatbot.
 * Java coding standards is followed throughout the code.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class Irwyn {

    private static TaskList taskList;
    private static Ui ui;
    private static StorageManager storageManager;
    private static final String filePath = System.getProperty("user.dir") + "/storage/taskData.txt";

    /**
     * Starts the Irwyn chatbot.
     * Instantiates a TaskManager object, Ui object, and a StorageManager Object.
     */
    private static void load() {
        try {
            ui = new Ui();
            storageManager = new StorageManager(filePath);
            taskList = new TaskList(storageManager.load());
            ui.start();
        } catch (Exception e) {
            System.out.println("Irwyn failed to start");
        }
    }

    /**
     * Runs the Irwyn chatbot.
     */
    public static void run(){
        boolean isExit = false;
        while (!isExit) {
            try {
                String commands = new Scanner(System.in).nextLine();
                Command c = CommandParser.parse(commands);
                c.execute(taskList, ui, storageManager);
                isExit = c.isExit();
            } catch (IrwynException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Irwyn.load();
        Irwyn.run();
    }
}
