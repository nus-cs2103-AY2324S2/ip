package Commands;

import Exceptions.InvalidFormatException;
import Exceptions.LeluException;
import TasksStorage.Storage;
import TasksStorage.TaskList;
import Ui.Ui;
public class FindCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        if (message.replaceAll(" ", "").equals("find")) {
            InvalidFormatException.callInvalidFormatException(LeluException.ErrorType.FIND);
        } else if (message.replaceFirst("find ", "").split(" ").length > 1) {
            throw new InvalidFormatException("   Enter only ONE keyword to search for your task:\n" +
                    "   find <keyword>\n");
        }
        TaskList res = tasks.filter(message.replaceFirst("find ", ""));
        System.out.println("   These are matching tasks in your list:\n   " + res.toString());
    }


}
