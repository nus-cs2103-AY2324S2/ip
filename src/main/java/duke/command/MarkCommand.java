package duke.command;

import duke.TaroException;
import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {

    private String input;
    public MarkCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Storage storage) throws TaroException {
        String[] words = input.split("\\s+");
        try {
            int index = Integer.parseInt(words[1]);
            String reply = tasks.markTask(index);
            return reply;
        } catch (NumberFormatException e) {
            return "Please enter a integer after the command 'mark'";
        }
    }
}
