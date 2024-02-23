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

/**
 * Chatbot will add a new event task.
 */
public class EventCommand implements Command {
    /** Handles UI messages that is to be displayed to user. */
    private final Ui ui;

    /** Contains all the tasks added by the user. */
    private final TaskList taskList;

    /** Interprets the meaning of the tokens parsed from the parser. */
    private final Interpreter intrpr;

    /**
     * Constructs the EventCommand with the specified fields.
     *
     * @param ui which handles display messages.
     * @param taskList which contains added tasks.
     * @param intrpr which contains the interpreter used to interpret the tokens.
     */
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
