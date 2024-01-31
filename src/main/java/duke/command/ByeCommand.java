package duke.command;

import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(Storage s, TaskList t, Ui u) {
        u.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}