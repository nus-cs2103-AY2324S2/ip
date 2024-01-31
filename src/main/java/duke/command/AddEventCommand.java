package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateTimeFormatException;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {

    private String arguments;

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentException, InvalidDateTimeFormatException {
        try {
            String[] splitTaskName = this.arguments.split(" /from ", 2);
            String[] splitFromToDates = splitTaskName[1].split(" /to ", 2);

            LocalDateTime fromDateTime = LocalDateTime.parse(splitFromToDates[0],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(splitFromToDates[1],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

            Task newTask = new Event(splitTaskName[0], fromDateTime, toDateTime);
            taskList.add(newTask);
            ui.addTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("EVENT");

        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("EVENT");
        }
    }
}
