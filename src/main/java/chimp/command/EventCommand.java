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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        tasks.add(text, fromDate, toDate);
        return ui.say(tasks.get(tasks.size() - 1), tasks);
    }

}
