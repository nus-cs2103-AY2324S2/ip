package command;
import duke.Ui;
import duke.TaskList;

import task.Deadline;
import exception.EmptyInputException;
import exception.EmptyTimeException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Command to add a deadline into the task list.
 */
public class deadlineCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of deadlineCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws EmptyInputException If user did not input description.
     * @throws EmptyTimeException If user did not input time.
     * @throws InvalidFormatException If user's input invalid format.
     * @throws InvalidDateTimeException If user input invalid date/time format.
     */
    public deadlineCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws
            EmptyInputException, EmptyTimeException, InvalidFormatException, InvalidDateTimeException  {
        String input = ui.getInput();
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("deadline");
        } else if (!input.contains("/by")) {
            throw new InvalidFormatException("deadline", "/by");
        } else {
            input = input.substring(8);
            String[] arrOfStr = input.split("/by");
            if (arrOfStr.length < 2) {
                throw new EmptyTimeException("deadline", "end");
            } else {
                try {
                    String description = arrOfStr[0].trim();
                    String by = arrOfStr[1].trim();
                    LocalDate date = LocalDate.parse(by.split(" ")[0].trim());
                    LocalTime time = LocalTime.parse(by.split(" ")[1].trim());
                    Deadline t = new Deadline(description, date, time);
                    taskList.deadline(t);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("deadline");
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
