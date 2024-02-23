package actions;

import static actions.CheckDuplicate.checkDuplicate;
import exceptionhandling.DukeException;
import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import static parser.Parser.parseDate;
import tasks.Deadline;
import ui.Duke;




/**
 * The `CreateDeadline` class implements the Action interface and represents an action to create a new Deadline task.
 * It stores the description and deadline date for the new Deadline task and provides a method to execute the action.
 */
public class CreateDeadline implements Action {

    private String desc;
    private LocalDate deadline;

    /**
     * Constructs a `CreateDeadline` object with the specified description and deadline date.
     *
     * @param command     The command string.
     */
    public CreateDeadline(String command) throws DukeException {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length <= 1) {
            throw new DukeException("Please write a description and a deadline for your task!");
        }
        String[] infoSplit = splitCommand[1].split("/by ", 2);
        if (infoSplit.length <= 1) {
            throw new DukeException("Please include a deadline by using by keyword like '/by Thursday'");
        }
        String deadlineDesc = infoSplit[0];
        try {
            LocalDate date = parseDate(infoSplit[1]);
            this.desc = deadlineDesc;
            this.deadline = date;
        } catch (DateTimeParseException e) {
            throw new DukeException("Date is in the wrong format! Follow yyyy-MM-dd format");
        }
    }

    /**
     * Executes the create deadline action by creating a new Deadline task
     * with the stored description and deadline date.
     * The new task is then added to the task list of the Duke chatbot.
     *
     * @param bot The Duke chatbot on which the action is to be executed.
     * @return A message indicating the result of the createdeadline action.
     */
    @Override
    public String execute(Duke bot) {
        assert(bot != null);
        assert(bot.getTaskList() != null);
        if (checkDuplicate(bot, this.desc, "D")) {
            return ("There is already a deadline with the same name!");
        }
        Deadline d = new Deadline(desc, deadline);
        bot.getTaskList().addToList(d);
        return ("Deadline successfully added!");
    }
}
