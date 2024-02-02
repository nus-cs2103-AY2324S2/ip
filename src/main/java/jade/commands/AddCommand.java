package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.ui.Ui;
import jade.storage.Storage;
import jade.exception.JadeException;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JadeException {
        tasks.add(task);
        ui.printMessage(String.format("\tGot it. I've added this task:\n\t %s\n\tNow you have %d task(s) in the list.", task, tasks.size()));
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
