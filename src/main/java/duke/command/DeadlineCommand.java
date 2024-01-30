package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] task = input.split("/by");
        try {
            Task t = new Deadline(task[0].substring(9).trim(), task[1].trim());
            list.add(t);
            ui.showAdded(t, list);
            storage.writeToFile(list);
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object a) {
        DeadlineCommand dc = (DeadlineCommand) a;
        return this.input.equals(dc.input);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
