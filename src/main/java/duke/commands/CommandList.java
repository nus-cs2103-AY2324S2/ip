package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandList extends Command {
    public CommandList() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] outputs = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputs[i] = String.format("%d. %s", i + 1, task);
        }

        String output = String.join("\n", outputs);
        ui.showMessage(output);
    }
}
