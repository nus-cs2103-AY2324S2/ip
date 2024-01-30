package command;

import utilities.Storage;
import task.TaskList;
import utilities.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        System.out.println("Bye! Hope to see you again!");
    }
}
