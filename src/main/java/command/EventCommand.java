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
import task.Event;

/**
 * Command to add an event into the task list.
 */
public class EventCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of eventCommand.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws EmptyInputException If user did not input description.
     * @throws EmptyTimeException If user did not input time.
     * @throws InvalidFormatException If user's input invalid format.
     * @throws InvalidDateTimeException If user input invalid date/time format.
     */
    public EventCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws
            EmptyInputException, EmptyTimeException, InvalidFormatException, InvalidDateTimeException {
        String input = ui.getInput();
        String str;
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("event");
        } else if (!input.contains("/from")) {
            throw new InvalidFormatException("event", "/from");
        } else if (!input.contains("/to")) {
            throw new InvalidFormatException("event", "/to");
        }
        String temp = input.substring(5);
        String[] arrOfStr = temp.split("/");
        if (arrOfStr.length < 2) {
            throw new EmptyTimeException("event", "start");
        } else if (arrOfStr.length < 3) {
            throw new EmptyTimeException("event", "end");
        }
        try {
            String description = arrOfStr[0].trim();
            String start = arrOfStr[1].substring(4).trim();
            LocalDate startDate = LocalDate.parse(start.split(" ")[0].trim());
            LocalTime startTime = LocalTime.parse(start.split(" ")[1].trim());
            String end = arrOfStr[2].substring(2).trim();
            LocalDate endDate = LocalDate.parse(end.split(" ")[0].trim());
            LocalTime endTime = LocalTime.parse(end.split(" ")[1].trim());
            if (startDate.compareTo(endDate) > 0) {
                throw new InvalidDateTimeException("unexpected date");
            } else if (startDate.compareTo(endDate) == 0 && startTime.compareTo(endTime) > 0) {
                throw new InvalidDateTimeException("unexpected time");
            }
            Event t = new Event(description, startDate, startTime, endDate, endTime);
            str = taskList.event(t);
            storage.writeTasks(taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event");
        }

        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
