package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String string;
    private String reply;
    public FindCommand(String string) { this.string = string; }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        reply = "";
        for (int i = 1; i <= tasks.getSize(); ++i) {
            Task currTask = tasks.getTaskByIndex(i);
            if (currTask.toString().contains(string)) {
                reply += (i + ". " + currTask.toString() + "\n");
            }
        }

        if (reply.equals("")) {
            ui.say("I couldn't find any matching tasks in the list :(");
        } else {
            ui.say("Here are the matching tasks in your list:\n" + reply);
        }
    }
}
