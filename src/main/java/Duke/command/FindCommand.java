package Duke.command;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;


public class FindCommand extends Command {
    private final String stringToFind;
    public FindCommand(String stringToFind) {
        this.stringToFind = stringToFind;
    }
    @Override
    public String execute(TaskList tsk, Ui ui, Storage storage) {
        String stringToPrint = "Here are the matching tasks in your list:\n";
        int counter = 0;
        for (int i = 0; i < tsk.accessList().size(); i++) {
            if (tsk.accessList().get(i).getMessage().contains(stringToFind)) {
                counter++;
                stringToPrint = stringToPrint + counter + ". " + tsk.accessList().get(i) + "\n";
            }
        }
        stringToPrint = stringToPrint.substring(0, stringToPrint.length() - 1);
        Ui.print_message(stringToPrint);
        return stringToPrint;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
