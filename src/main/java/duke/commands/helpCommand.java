package commands;

import tasks.TaskList;
import ui.Ui;

public class helpCommand extends Command{
    public helpCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        System.err.println("Acceptable commands are: todo, deadline, event, list, mark, unmark, save, delete, bye");
        return true;
    }
}
