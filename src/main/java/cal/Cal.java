package cal;
import java.util.Scanner;

import commands.Command;
import exceptions.CalException;
import parser.Parser;
import storage.StorageManager;
import tasklist.TaskList;
import ui.Ui;

/**
 * Cal is a chatbot that helps user to manage tasks.
 */
public class Cal {
    private static TaskList tasks;
    private static StorageManager storageManager;
    private static Ui ui;

    /**
     * Constructs a new Cal instance.
     */
    public Cal() {
        storageManager = new StorageManager();
        tasks = storageManager.load();
        ui = new Ui();
    }

    /**
     * Starts the chatbot. User enter command
     * Cal will process each command until 'bye' is given.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand(sc);
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, storageManager);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showErrorMsg(e);
            } finally {
                ui.showLine();
            }
        }
        sc.close();
    }

    public String getResponse(String fullCommand) throws CalException {
        Command c = Parser.parseCommand(fullCommand);
        return c.execute(tasks, storageManager);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Cal().run();
    }
}
