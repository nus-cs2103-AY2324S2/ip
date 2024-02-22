package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import storage.Storage;
import tasks.*;
import ui.Ui;

public class UpdateCommand extends Command {
    private static final String COMMAND = "update ";
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.UPDATE);
        int index = 0;
        Task t = parse(message, index);
        return tasks.updateTask(index, t);
    }
    private Task parse(String message, int index) throws InvalidFormatException {
        String[] details = message.split("/");
        String number = details[0].split(" ")[1];
        index = getTaskListNumber(number, LeluException.ErrorType.UPDATE);
        switch (details.length) {
        case 3:
            if (details[1].equals("todo")) {
                return new ToDo(details[2]);
            }
            break;
        case 5:
            if (details[1].equals("deadline")) {
                return new Deadline(details[2], details[4]);
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
