package asher.Commands;

import asher.Ui.Ui;
import asher.Tasks.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        storage.writeToFile(taskList);
        return ui.showExitMessage();
    }
}
