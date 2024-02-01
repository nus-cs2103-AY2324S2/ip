package command;
import duke.Ui;
import duke.TaskList;

import task.Event;
import exception.EmptyInputException;
import exception.EmptyTimeException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public EventCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws
            EmptyInputException, EmptyTimeException, InvalidFormatException, InvalidDateTimeException  {
        String input = ui.getInput();
        if (input.split(" ").length == 1) {
            throw new EmptyInputException("event");
        } else if (!input.contains("/from")) {
            throw new InvalidFormatException("event", "/from");
        } else if (!input.contains("/to")) {
            throw new InvalidFormatException("event", "/to");
        } else {
            String temp = input.substring(5);
            String[] arrOfStr = temp.split("/");
            if (arrOfStr.length < 2) {
                throw new EmptyTimeException("event", "start");
            } else if (arrOfStr.length < 3) {
                throw new EmptyTimeException("event", "end");
            } else {
                try {
                    String description = arrOfStr[0].trim();
                    String start = arrOfStr[1].substring(4).trim();
                    LocalDate startDate = LocalDate.parse(start.split(" ")[0].trim());
                    LocalTime startTime = LocalTime.parse(start.split(" ")[1].trim());
                    String end = arrOfStr[2].substring(2).trim();
                    LocalDate endDate = LocalDate.parse(end.split(" ")[0].trim());
                    LocalTime endTime = LocalTime.parse(end.split(" ")[1].trim());
                    Event t = new Event(description, startDate, startTime, endDate, endTime);
                    taskList.event(t);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException("event");
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
