package duke;

import java.util.Scanner;
import duke.command.Command;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for Duke class.
     * Instantiates Ui and Storage. Loads TaskList from filepath or makes relevant directory and file if required.
     *
     * @param filePath Filepath for loading and saving TaskList data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage (filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IllegalArgumentException e) {
            System.out.println("Couldn't read from file");
            tasks = new TaskList();
        }
    }

    /**
     * Helper function that manages the running of the chatbot.
     */
    public void run () {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.handleInput(scanner.nextLine());
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }


}