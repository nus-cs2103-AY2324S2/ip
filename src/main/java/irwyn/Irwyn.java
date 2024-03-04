package irwyn;

import java.util.Scanner;

import commands.Command;
import commands.CommandParser;
import irwyn.exceptions.CommandException;
import irwyn.exceptions.IrwynException;
import irwyn.exceptions.TaskException;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates Irwyn chatbot.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class Irwyn {

    private static TaskList taskList;
    private static Ui ui;
    private static StorageManager storageManager;
    private static final String filePath = System.getProperty("user.dir") + "/storage/taskData.txt";

    public Irwyn() {
        Irwyn.load();
    }
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
    public static void run() {
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

    /**
     * Gets the response from the Irwyn chatbot to be displayed on the GUI.
     *
     * @param input The input from the user using the GUI.
     * @return Response string to be displayed on the GUI.
     */
    public String getResponse(String input) {
        try {
            Command c = CommandParser.parse(input);
            return c.execute(storageManager, ui, taskList);
        } catch (TaskException e) {
            return ui.getReply(e.getMessage());
        } catch (CommandException e) {
            return ui.getReply(e.getMessage());
        } catch (IrwynException e) {
            return ui.getReply(e.getMessage());
        }
    }
}
