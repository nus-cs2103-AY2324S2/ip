package capone.commands;

import java.util.Scanner;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;

public class ByeCommand extends Command {
    private final Scanner scanner;

    public ByeCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        ui.sendMessage("Bye. Hope to see you again soon!\n");
        scanner.close();
        System.exit(0);
    }
}
