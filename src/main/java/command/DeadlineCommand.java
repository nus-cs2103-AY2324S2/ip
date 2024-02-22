package command;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;
import exception.EmptyTimeException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;
import task.Deadline;

/**
 * Command to add a deadline into the task list.
 */
public class DeadlineCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of deadlineCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws EmptyInputException If user did not input description.
     * @throws EmptyTimeException If user did not input time.
     * @throws InvalidFormatException If user's input invalid format.
     * @throws InvalidDateTimeException If user input invalid date/time format.
     */
    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws
            EmptyInputException, EmptyTimeException, InvalidFormatException, InvalidDateTimeException {
        String str;
        String input = ui.getInput();
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("deadline");
        } else if (!input.contains("/by")) {
            throw new InvalidFormatException("deadline", "/by");
        }
        input = input.substring(8);
        String[] arrOfStr = input.split("/by");
        if (arrOfStr.length < 2) {
            throw new EmptyTimeException("deadline", "end");
        }
        try {
            String description = arrOfStr[0].trim();
            String by = arrOfStr[1].trim();
            LocalDate date = LocalDate.parse(by.split(" ")[0].trim());
            LocalTime time = LocalTime.parse(by.split(" ")[1].trim());
            Deadline t = new Deadline(description, date, time);
            str = taskList.deadline(t);
            storage.writeTasks(taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("deadline");
        }

        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
