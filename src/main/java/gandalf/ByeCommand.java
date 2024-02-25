package gandalf;

import java.util.Scanner;

/**
 * Executes the bye command class
 */
public class ByeCommand extends Command {

    private Scanner scanner;
    public ByeCommand(String commandName, TaskList tasks, Storage storage, Ui ui, Scanner scanner) {
        super(commandName, tasks, storage, ui);
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        this.scanner.close();
        ui.bye();
    }

    @Override
    public boolean setExit() {
        return true;
    }
}
