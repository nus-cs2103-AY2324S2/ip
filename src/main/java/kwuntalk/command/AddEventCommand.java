package kwuntalk.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;
import kwuntalk.exception.DuplicateTaskException;
import kwuntalk.exception.InvalidArgumentException;
import kwuntalk.exception.InvalidDateTimeFormatException;
import kwuntalk.exception.TasksFileException;
import kwuntalk.task.Event;
import kwuntalk.task.Task;


/**
 * Represents the command to add event task.
 */
public class AddEventCommand extends Command {

    private String arguments;


    /**
     * Constructor for AddEventCommand.
     *
     * @param arguments Argument of command.
     */
    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Executes the event command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     * @throws InvalidArgumentException If command has invalid or missing arguments.
     * @throws InvalidDateTimeFormatException If DateTime stated has an invalid format.
     * @throws DuplicateTaskException If there exist a task with the same name.
     * @throws TasksFileException If tasks file can't be written to for saving of data into storage.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, InvalidDateTimeFormatException, DuplicateTaskException,
                TasksFileException {

        try {
            String taskName = this.arguments.split(" /from ", 2)[0];
            String[] datesArgParts = this.arguments.split(" /from ", 2)[1].split(" /to ", 2);

            LocalDateTime fromDateTime = LocalDateTime.parse(datesArgParts[0],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(datesArgParts[1],
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

            Task newTask = new Event(taskName, fromDateTime, toDateTime);
            taskList.add(newTask);
            storage.saveTasksFile(taskList);
            return ui.showAddTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("EVENT");

        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException("EVENT");

        } catch (IOException e) {
            throw new TasksFileException();
        }
    }
}
