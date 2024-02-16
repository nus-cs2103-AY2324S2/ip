package chaterpillar.commands;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

public class UpdateCommand extends Command {
    private final int index;
    private final String updatedName;
    private final String updatedDate;
    private final String updatedStartDate;
    private final String updatedEndDate;

    public UpdateCommand(int index, String updatedName, String updatedDate,
                         String updatedStartDate, String updatedEndDate) {
        this.index = index;
        this.updatedName = updatedName;
        this.updatedDate = updatedDate;
        this.updatedStartDate = updatedStartDate;
        this.updatedEndDate = updatedEndDate;
        assert index > 0;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        Task task = tasks.get(index);

        task.updateName(updatedName);
        task.updateDate(updatedDate);
        task.updateStartDate(updatedStartDate);
        task.updateEndDate(updatedEndDate);
        storage.saveAllToFile(tasks);

        String output =
                "Okay! I have updated the following task: \n"
                + (index + 1) + ". " + task;

        ui.echo(output);
        return output;
    }
}
