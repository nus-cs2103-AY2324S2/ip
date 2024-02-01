package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateTimeFormatException;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents the command to add deadline task.
 */
public class AddDeadlineCommand extends Command {

    private String arguments;


    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param arguments Argument of command.
     */
    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Executes the deadline command.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @throws InvalidArgumentException If command has invalid or missing arguments.
     * @throws InvalidDateTimeFormatException If DateTime stated has an invalid format.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, InvalidDateTimeFormatException {

        try {
            String[] splitDate = this.arguments.split(" /by ", 2);

            LocalDateTime dueDateTime = LocalDateTime.parse(splitDate[1],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

            Task newTask = new Deadline(splitDate[0], dueDateTime);
            taskList.add(newTask);
            ui.addTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("DEADLINE");

        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("DEADLINE");
        }
    }
}
