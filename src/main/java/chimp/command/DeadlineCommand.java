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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CommandExecuteException {
        tasks.add(text, date);
        return ui.say(tasks.get(tasks.size() - 1), tasks);
    }

}
