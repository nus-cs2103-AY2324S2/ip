package commands;

import exception.DeadlineFormatException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private static final String SUCCESS_MESSAGE = "Got it. Uncle added this deadline:\n\t\t %s " +
            "\n\t Now you have %s task(s) in the list.";
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

            try {
                Task deadline = new Deadline(desc, LocalDate.parse(by));
                tasks.addTasks(deadline);
                try {
                    storage.appendToFile(tasks);
                } catch (IOException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                ui.showToUser(String.format(SUCCESS_MESSAGE, deadline, tasks.numTasks()));
            } catch (DateTimeParseException e) {
                throw new DeadlineFormatException();
            }
        }
    }
}