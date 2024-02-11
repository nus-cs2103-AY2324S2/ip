package shodan;

import java.util.Scanner;

import shodan.command.Command;
import shodan.command.CommandParser;
import shodan.storage.StorageManager;
import shodan.ui.TermUi;

/**
 * Shodan is a chatbot that is able to manage tasks.
 */
public class Shodan {
    private static StorageManager storageManager;
    private TaskList tasks;
    private TermUi ui;

    /**
     * Instantiates a new Shodan instance.
     */
    public Shodan() {
        storageManager = new StorageManager();
        tasks = new TaskList(storageManager.loadTasks());
        ui = new TermUi();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Shodan().run();
    }

    /**
     * Starts the chatbot. Shodan will process each command until the 'bye'
     * command is entered.
     */
    public void run() {
        ui.showGreeting();
        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                ui.showPrompt();
                Command command = CommandParser.parse(sc.nextLine());
                shouldExit = command.execute(tasks, storageManager, ui);
            } catch (ShodanException e) {
                ui.printError(e);

            } catch (IllegalArgumentException e) {
                /* If no input was entered, re-prompt without showing any error */
            }
        }
    }
}
