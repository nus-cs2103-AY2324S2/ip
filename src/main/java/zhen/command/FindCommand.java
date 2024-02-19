package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;


public class FindCommand extends Command {
    private final String stringToFind;
    public FindCommand(String stringToFind) {
        this.stringToFind = stringToFind;
    }
    @Override
    public String execute(TaskList tsk, Ui ui, Storage storage) {
        String stringToPrint = "Here are the matching tasks in your list:\n";
        int counter = 0;
        for (int i = 0; i < tsk.getTasks().size(); i++) {
            if (tsk.getTasks().get(i).getMessage().contains(stringToFind)) {
                counter++;
                stringToPrint = stringToPrint + counter + ". " + tsk.getTasks().get(i) + "\n";
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
