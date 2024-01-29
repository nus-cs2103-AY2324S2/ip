package commands;

import exception.EventFormatException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private static final String SUCCESS_MESSAGE = "Got it. Uncle added this event:\n\t\t %s"
            + "\n\t Now you have %s task(s) in the list.";
    private final String message;

    public EventCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws EventFormatException {
        String[] args = message.split("/from");
        if (args.length == 1 || args.length > 2) {
            throw new EventFormatException();
        } else {
            String desc = args[0].trim();
            String[] duration = args[1].split("/to");
            if (duration.length == 1 || duration.length > 2) {
                throw new EventFormatException();
            } else {
                String start = duration[0].trim();
                String end = duration[1].trim();
                Task event = new Event(desc, LocalDate.parse(start), LocalDate.parse(end));
                tasks.addTasks(event);
                try {
                    storage.appendToFile(tasks);
                } catch (IOException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                ui.showToUser(String.format(SUCCESS_MESSAGE, event, tasks.numTasks()));
            }
        }
    }
}

