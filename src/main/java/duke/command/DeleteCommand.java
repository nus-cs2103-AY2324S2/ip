package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to delete specified task.
 */
public class DeleteCommand implements Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Removes task in TaskList where index is indicated in input.
     *
     * @param list Holds the existing tasks including task to be removed.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException If index is not n range.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        String[] s = input.split("\\s");
        assert (s.length == 2) : "Delete command cannot be split at space";
        int num = Integer.parseInt(s[1]);
        if (num <= list.getSize() && num >= 1) {
            Task t = list.delete(num - 1);
            storage.writeToFile(list);
            return ui.showDeleted(t, list);
        } else {
            throw new DukeException("Task (" + num + ") not found.\n" + list.print());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object a) {
        DeleteCommand dc = (DeleteCommand) a;
        return this.input.equals(dc.input);
    }
}
