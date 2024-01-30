package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.commands.Command;
import nollid.exceptions.NollidException;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final ArrayList<String> argsList;

    public UnmarkCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        // This means that the user has not supplied any number with the command
        if (this.argsList.size() == 1) {
            throw new NollidException("Please enter the task you wish to mark as not done!\n"
                    + "Usage: unmark [task number]");
        } else {
            try {
                int taskIndex = Integer.parseInt(this.argsList.get(1));
                tasks.setDone(taskIndex, false);

                String response = "Alright, I've marked this task as not done yet: \n"
                        + "\t " + tasks.get(taskIndex - 1).toString();

                ui.sendMessage(response);
                storage.update(tasks);
            } catch (NumberFormatException e) {
                throw new NollidException("Please enter a number for the unmark command.\n"
                        + "Usage: unmark [task number]");
            } catch (IndexOutOfBoundsException e) {
                throw new NollidException("Are you sure that's a valid task number? (Tip: use 'list' to check the "
                        + "number of your task!)\n" + "Usage: unmark [task number]");
            }
        }
    }
}
