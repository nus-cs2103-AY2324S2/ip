package gandalf.commands;

import gandalf.Storage;
import gandalf.TaskList;
import gandalf.Ui;

/**
 * SumCommand shows the total amount spent on certain expenses.
 */
public class SumCommand extends Command {
    private final String expensesName;

    public SumCommand(String commandName, TaskList tasks, Storage storage, Ui ui, String expensesName) {
        super(commandName, tasks, storage, ui);
        this.expensesName = expensesName;
    }

    @Override
    public String execute() {
        double totalSum = tasks.sumExpenses(expensesName);
        return ui.showExpenses(this.expensesName, totalSum);
    }

}
