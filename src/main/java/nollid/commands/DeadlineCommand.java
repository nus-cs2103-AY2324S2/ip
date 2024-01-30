package nollid.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;
import nollid.exceptions.NollidException;
import nollid.tasks.Deadline;

/**
 * DeadlineCommand class represents a command for adding a Deadline task.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class DeadlineCommand extends Command {
    private final ArrayList<String> argsList;

    public DeadlineCommand(ArrayList<String> argsList) {
        this.argsList = argsList;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NollidException {
        int byIndex = this.argsList.indexOf("/by");
        if (this.argsList.size() == 1 || byIndex == 1) {
            throw new NollidException("Deadline description cannot be empty!\n"
                    + Deadline.USAGE_HINT);
        }

        if (byIndex == this.argsList.size() - 1 || byIndex == -1) {
            throw new NollidException("Please input a deadline!\n"
                    + Deadline.USAGE_HINT);
        }

        StringBuilder taskDescription = new StringBuilder();
        for (int i = 1; i < byIndex; i++) {
            if (i != byIndex - 1) {
                taskDescription.append(this.argsList.get(i)).append(" ");
            } else {
                taskDescription.append(this.argsList.get(i));
            }
        }

        StringBuilder deadlineString = new StringBuilder();
        for (int i = byIndex + 1; i < this.argsList.size(); i++) {
            if (i != this.argsList.size() - 1) {
                deadlineString.append(this.argsList.get(i)).append(" ");
            } else {
                deadlineString.append(this.argsList.get(i));
            }
        }

        try {
            LocalDateTime deadline = Parser.getLocalDateTimeFromString(deadlineString.toString());
            Deadline task = new Deadline(taskDescription.toString(), deadline);
            tasks.add(task);

            String message = "Alright, added:\n" + "\t" + task + "\n";
            message += tasks.summary();
            ui.sendMessage(message);
            storage.update(tasks);
        } catch (DateTimeParseException e) {
            throw new NollidException("Unrecognized deadline format\n"
                    + Deadline.USAGE_HINT);
        }
    }
}
