package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to add new deadline.
 */
public class DeadlineCommand implements Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Deadline based on description and date time extracted from input and adds to TaskList.
     *
     * @param list Holds the tasks added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException If date time format is not valid.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] task = input.split("/by");
        assert (task.length == 2) : "Deadline command cannot be split at /by";
        try {
            Task t = new Deadline(task[0].substring(9).trim(), task[1].trim());
            list.add(t);
            storage.writeToFile(list);
            return ui.showAdded(t, list);
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
