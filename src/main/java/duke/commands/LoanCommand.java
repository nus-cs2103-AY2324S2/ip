package duke.commands;

import duke.ChatSession;
import duke.tasks.Loan;

/**
 * LoanCommand class to handle the execution of loan command
 */
public class LoanCommand implements NamedCommand {
    private static final String NAME = "loan";

    public String getName() {
        return LoanCommand.NAME;
    }

    /**
     * Adds a loan to the task list
     *
     * @param session     Chat session
     * @param commandArgs Command arguments
     */
    public void execute(ChatSession session, String commandArgs) {
        String[] args = commandArgs.split(" ");
        if (args.length != 2) {
            session.printMessage("Invalid number of arguments for loan command");
            return;
        }
        String name = args[0];
        try {
            int amount = Integer.valueOf(args[1]);
            if (amount < 0) {
                session.printMessage("Amount cannot be negative");
                return;
            }
        } catch (NumberFormatException e) {
            session.printMessage("Invalid amount for loan");
            return;
        }

        session.getTaskList().add(new Loan(name, Integer.valueOf(args[1])));
        session.printMessage("Got it. I've added the following loan:" + System.lineSeparator() + name);
    }
}
