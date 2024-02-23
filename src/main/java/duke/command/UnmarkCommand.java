package duke.command;

import duke.TaroException;
import duke.Storage;
import duke.TaskList;

public class UnmarkCommand extends Command {

    private String input;
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command and returns the result.
     * @param tasks the list of tasks
     * @param storage the storage object
     * @return A string that determines whether the task has been unmarked successfully
     */
    public String execute(TaskList tasks, Storage storage) throws TaroException {
        String[] words = input.split("\\s+");
        try {
            int index = Integer.parseInt(words[1]);
            String reply = tasks.unmarkTask(index);
            return reply;
        } catch (NumberFormatException e) {
            return "Please enter a integer after the command 'unmark'";
        }
    }
}
