package goldbot.commands;

import java.security.InvalidParameterException;

import goldbot.ChatSession;
import goldbot.exceptions.TooMuchPaidException;
import goldbot.tasks.Loan;
import goldbot.tasks.Task;

/**
 * Pay class to allow user to pay loans
 */
public class Pay implements NamedCommand {
    public String getName() {
        return "pay";
    }

    public void execute(ChatSession session, String commandArgs) {
        try {
            String[] args = commandArgs.split(" ");
            if (args.length != 2) {
                throw new InvalidParameterException("Invalid number of arguments for pay command");
            }
            int index = Integer.parseInt(args[0]);
            int amount = Integer.parseInt(args[1]);
            if (amount < 0) {
                throw new InvalidParameterException("Amount cannot be negative");
            }
            Task t = session.getTaskList().getTask(index);
            if (t instanceof Loan) {
                // CHECKSTYLE.OFF: SeparatorWrap
                ((Loan) t).pay(amount);
                // CHECKSTYLE.ON: SeparatorWrap
                session.printMessage("Nice! I've paid off this loan:" + System.lineSeparator() + t.getName());
            } else {
                throw new InvalidParameterException("Task is not a loan");
            }
        } catch (NumberFormatException e) {
            session.printMessage("Invalid arguments for pay command");
        } catch (InvalidParameterException e) {
            session.printMessage(e.getMessage());
        } catch (TooMuchPaidException e) {
            session.printMessage(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            session.printMessage("Invalid index for pay command");
        }
    }
}
