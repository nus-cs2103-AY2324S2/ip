package sam.command;

import sam.SamException;
import sam.Storage;
import sam.TaskList;
import sam.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String index) throws SamException {
        if (index.isBlank()) {
            throw new SamException("Please provide a task number.");
        }
        try {
            this.index = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new SamException("Please provide a number");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SamException {
        tasks.markTask(index);
        storage.save(tasks);
    }
}