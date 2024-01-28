package commands;

import exception.DeadlineFormatException;
import exception.UncleBobException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private final String message;

    public DeadlineCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DeadlineFormatException {
        String[] args = message.split("/by");
        if (args.length == 1 || args.length > 2) {
            throw new DeadlineFormatException();
        } else {
            String desc = args[0].trim();
            String by = args[1].trim();
            Task deadline = new Deadline(desc, LocalDate.parse(by));
            tasks.addTasks(deadline);
            try {
                storage.appendToFile(deadline.getSymbol() + "/" + deadline.getStatus() +
                        "/" + desc + "/" + by + "\n");
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}