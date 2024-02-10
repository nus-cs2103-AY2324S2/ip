package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class helpCommand extends Command{
    public helpCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
        System.err.println("Acceptable commands are: todo, deadline, event, list, mark, unmark, save, delete, bye");
        return true;
    }
}
