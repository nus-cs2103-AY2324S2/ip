package chimp.command;

import java.time.LocalDate;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;
import chimp.exception.CommandExecuteException;

public class DeadlineCommand extends Command {
    String text;
    LocalDate date;

    public DeadlineCommand(String text, LocalDate date) {
        this.text = text;
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws CommandExecuteException {
        list.add(text, date);
        ui.say(list.get(list.size() - 1), list);
    }

}
