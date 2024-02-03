package eggy.command;

import eggy.exception.EggyException;
import eggy.exception.IncompleteTaskException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Deadline;
import eggy.ui.Ui;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String name;
    private LocalDateTime by;

    public DeadlineCommand(String[] commands) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("deadline");
        }
        String[] deadlineSplit = commands[1].split(" /by ");
        if (deadlineSplit.length < 2) {
            throw new IncompleteTaskException("deadline");
        }
        this.name = deadlineSplit[0];
        this.by = Command.parseDateTime(deadlineSplit[1]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Deadline newDeadline = new Deadline(this.name, this.by);
        tasks.addTask(newDeadline);
        ui.printTaskAdded(newDeadline, tasks.getSize());
        storage.save(tasks);
    }

}
