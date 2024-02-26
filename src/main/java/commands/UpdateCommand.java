package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import storage.Storage;
import tasks.*;
import ui.Ui;

public class UpdateCommand extends Command {
    private static final String COMMAND = "update ";
    private int index;
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.UPDATE);
        Task t = parse(message);
        return tasks.updateTask(this.index, t);
    }
    private Task parse(String message) throws InvalidFormatException {
        String[] details = message.split("/");
        String number = details[0].split(" ")[1];
        this.index = getTaskListNumber(number, LeluException.ErrorType.UPDATE);
        switch (details.length) {
        case 3:
            if (details[1].equals("todo")) {
                return new ToDo(details[2]);
            }
            break;
        case 5:
            if (details[1].equals("deadline")) {
                return new Deadline(details[2].trim(), details[4].trim());
            }
        case 7:
            if (details[1].equals("event")) {
                return new Event(details[2], details[4], details[6]);
            }
            break;
        default:
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.UPDATE);
        }
        InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.UPDATE);
        return null;
    }
}
