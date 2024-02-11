import java.util.Scanner;

import harvard.Parser;
import harvard.Storage;
import harvard.TaskList;
import harvard.Ui;
import harvard.exceptions.HarvardException;


/**
 * The Harvard class represents a simple task management application
 * that allows users to interact with tasks through a command-line interface.
 * It utilizes a Parser to interpret user commands, a Storage to manage data persistence,
 * a TaskList to handle tasks, and a Ui to display messages to the user.
 */
public class Harvard {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Harvard object with the specified file path.
     *
     * @param filePath the file path where task data is stored or to be stored
     */
    public Harvard(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Harvard application, displaying welcome message,
     * taking user input, parsing commands, and displaying goodbye message
     * when the user exits.
     */
    public void run() {
        ui.showWelcome();
        Parser parser = new Parser(storage, tasks, ui);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();

            if (echoInput.equals("bye")) {
                break;
            }

            try {
                parser.parse(echoInput);
            } catch (HarvardException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * The main method to start the Harvard application.
     * Initializes a Harvard object with the default data file path
     * and runs the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Harvard(System.getProperty("user.dir") + "/data/harvard.txt").run();
    }


}

