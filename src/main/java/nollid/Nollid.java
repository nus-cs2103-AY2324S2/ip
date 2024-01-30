package nollid;

import java.nio.file.Path;
import java.util.Scanner;

import nollid.commands.Command;
import nollid.exceptions.InvalidCommandException;
import nollid.exceptions.NollidException;

/**
 * Nollid class represents the main application class for Nollid task manager.
 * It initializes the UI, Storage, and TaskList, and runs the main loop to process user commands.
 */
public class Nollid {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Nollid object with the specified file path for data storage.
     *
     * @param filePath The path to the file used for data storage.
     */
    public Nollid(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    public static void main(String[] args) {
        new Nollid(Storage.DEFAULT_FILEPATH).run();
    }

    /**
     * Runs the main loop to process user commands and interact with the user.
     */
    public void run() {
        this.ui.sendWelcomeMessage();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String fullCommand = this.ui.readCommand(scanner);
            Command command;
            try {
                command = Parser.parse(fullCommand);
            } catch (InvalidCommandException e) {
                this.ui.sendMessage("Invalid command! Type 'help' to view a list of commands.");
                continue;
            }

            try {
                command.execute(this.tasks, this.ui, this.storage);
            } catch (NollidException e) {
                this.ui.sendMessage(e.getMessage());
            }
        }
    }
}
