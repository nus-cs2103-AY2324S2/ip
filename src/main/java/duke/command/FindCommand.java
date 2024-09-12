package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

/**
 * Class representing Find commands.
 */
public class FindCommand extends Command {
    private String string;
    private String reply;
    public FindCommand(String string) { this.string = string; }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        reply = "";
        for (int i = 1; i <= tasks.getSize(); ++i) {
            Task currTask = tasks.getTaskByIndex(i);
            if (currTask.toString().contains(string)) {
                reply += (i + ". " + currTask.toString() + "\n");
            }
        }

        if (reply.equals("")) {
            return "I couldn't find any matching tasks in the list :(";
        } else {
            return "Here are the matching tasks in your list:\n" + reply;
        }
    }
}
