package chimp.command;

import java.time.LocalDate;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class EventCommand extends Command {
    String text;
    LocalDate fromDate;
    LocalDate toDate;

    public EventCommand(String text, LocalDate fromDate, LocalDate toDate) {
        this.text = text;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws CommandExecuteException {
        list.add(text, fromDate, toDate);
        ui.say(list.get(list.size() - 1), list);
    }

}
