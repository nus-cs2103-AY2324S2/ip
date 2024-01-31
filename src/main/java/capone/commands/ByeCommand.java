package capone.commands;

import capone.exceptions.CaponeException;
import capone.TaskList;
import capone.TaskStorage;
import capone.Ui;

import java.util.Scanner;

public class ByeCommand extends Command {
    private final Scanner scanner;

    public ByeCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, TaskStorage storage) throws CaponeException {
        ui.sendMessage("Bye. Hope to see you again soon!");
        scanner.close();
        System.exit(0);
    }
}
