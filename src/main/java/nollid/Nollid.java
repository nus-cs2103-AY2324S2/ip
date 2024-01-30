package nollid;

import nollid.commands.Command;
import nollid.exceptions.InvalidCommandException;
import nollid.exceptions.NollidException;

import java.nio.file.Path;
import java.util.Scanner;

public class Nollid {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Nollid(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.load());
    }

    public static void main(String[] args) {
        new Nollid(Storage.DEFAULT_FILEPATH).run();
    }

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