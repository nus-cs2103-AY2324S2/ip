package shodan;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    private ByteArrayOutputStream textOutputHook = new ByteArrayOutputStream();

    /**
     * Instantiates a new Shodan instance.
     */
    public Shodan(boolean useGui) {
        storageManager = new StorageManager();
        tasks = new TaskList(storageManager.loadTasks());
        if (useGui) {
            ui = new TermUi(new PrintStream(textOutputHook));
        } else {
            ui = new TermUi(System.out);
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Shodan(false).run();
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

    public String getResponse(String input) {
        try {
            textOutputHook.reset();
            Command command = CommandParser.parse(input);
            command.execute(tasks, storageManager, ui);
            return textOutputHook.toString();
        } catch (ShodanException e) {
            return e.getMessage();
        }
    }
}
