package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    private String input;
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command and returns the result.
     * @param tasks the list of tasks
     * @param storage the storage object
     * @return A string that determines whether the task has been deleted successfully
     * @throws IOException if there is an error writing to the file
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] words = input.split("\\s+");
        try {
            int index = Integer.parseInt(words[1]);
            String reply = tasks.deleteTask(index);
            return reply;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a integer after the command 'delete'");
        }
    }
}
