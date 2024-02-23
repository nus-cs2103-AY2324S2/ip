package simpli.commands;

import simpli.commands.base.Command;
import simpli.commands.base.CommandResult;
import simpli.commands.base.CommandWord;
import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.tasks.Task;
import simpli.tasks.TaskList;
import simpli.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand implements Command {
    private final Ui ui;
    private final TaskList taskList;
    private final Interpreter intrpr;

    public EventCommand(Ui ui, TaskList taskList, Interpreter intrpr) {
        this.ui = ui;
        this.taskList = taskList;
        this.intrpr = intrpr;
    }
    @Override
    public CommandResult execute(String[] tokens) throws CommandException, TaskException {
        LocalDateTime[] dates = intrpr.interpretLocalDateTime(tokens);
        if (!intrpr.isValidDateTime(dates)) {
            throw new TaskException();
        }
        Task addedTask = taskList.addEvent(tokens, dates);
        taskList.sort();
        return new CommandResult(
                CommandWord.EVENT,
                ui.getAddedTaskMessage(addedTask)
        );
    }
}
