package gandalf.commands;

import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;

import java.util.Scanner;

/**
 * Executes the bye command class
 */
public class ByeCommand extends Command {

    public ByeCommand(String commandName, TaskList tasks, Storage storage, Ui ui) {
        super(commandName, tasks, storage, ui);
    }

    @Override
    public String execute() {
        return ui.bye();
    }

    @Override
    public boolean setExit() {
        return true;
    }
}
