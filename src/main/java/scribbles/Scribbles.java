package scribbles;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import scribbles.parser.Parser;
import scribbles.storage.Storage;
import scribbles.task.Deadline;
import scribbles.task.Event;
import scribbles.task.Todo;
import scribbles.tasklist.TaskList;
import scribbles.ui.Ui;

/**
 * This class implements the chatbot Scribbles.
 *
 * @author danielle
 */
public class Scribbles {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filePath;

    /**
     * Constructs a new Scribbles object with the specified file path.
     */
    public Scribbles(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(this.filePath, taskList);
    }

    public String greet() {
        return ui.greet();
    }

    /**
     * Takes the input of user as command and executes the list of actions that should follow the given command.
     *
     * @param input The command input by user.
     * @return false if command is "bye", true otherwise.
     */
    public String getResponse(String input) {
        Parser parsedInput = new Parser(input);
        String command = parsedInput.getCommand();

        switch (command) {
        case "bye":
            return ui.printExitMessage();
        case "list":
            return ui.listTasks(taskList);
        case "mark":
            try {
                int index = parsedInput.getIndex();
                taskList.get(index - 1).markComplete();
                storage.saveFileData(taskList);
                return ui.printMarkCompletedMessage(index, taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printInvalidIndexMessage(taskList);
            } catch (FileNotFoundException e) {
                return ui.printFileNotFoundMessage();
            }
        case "unmark":
            try {
                int index = parsedInput.getIndex();
                taskList.get(index - 1).markIncomplete();
                storage.saveFileData(taskList);
                return ui.printMarkIncompleteMessage(index, taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printInvalidIndexMessage(taskList);
            } catch (FileNotFoundException e) {
                return ui.printFileNotFoundMessage();
            }
        case "todo":
            try {
                String description = parsedInput.getTodoDescription();
                taskList.addTask(new Todo(description, false));
                storage.saveFileData(taskList);
                return ui.confirmTaskAddition(taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printTaskMissingInformationMessage();
            } catch (FileNotFoundException e) {
                return ui.printFileNotFoundMessage();
            }
        case "deadline":
            if (parsedInput.isMissingDeadlineInformation()) {
                return ui.printTaskMissingInformationMessage();
            } else {
                try {
                    String description = parsedInput.getDeadlineDescription();
                    LocalDateTime by = parsedInput.getDeadlineBy();
                    taskList.addTask(new Deadline(description, false, by));
                    storage.saveFileData(taskList);
                    return ui.confirmTaskAddition(taskList);
                } catch (IndexOutOfBoundsException e) {
                    return ui.printTaskMissingInformationMessage();
                } catch (DateTimeParseException e) {
                    return ui.printWrongDateTimeFormatMessage();
                } catch (FileNotFoundException e) {
                    return ui.printFileNotFoundMessage();
                }
            }
        case "event":
            if (parsedInput.isInvalidEvent()) {
                ui.printTaskMissingInformationMessage();
            } else {
                try {
                    String description = parsedInput.getEventDescription();
                    LocalDateTime start = parsedInput.getStartDateTime();
                    LocalDateTime end = parsedInput.getEndDateTime();
                    taskList.addTask(new Event(description, false, start, end));
                    storage.saveFileData(taskList);
                    return ui.confirmTaskAddition(taskList);
                } catch (IndexOutOfBoundsException e) {
                    return ui.printTaskMissingInformationMessage();
                } catch (DateTimeParseException e) {
                    return ui.printWrongDateTimeFormatMessage();
                } catch (FileNotFoundException e) {
                    return ui.printFileNotFoundMessage();
                }
            }
            break;
        case "delete":
            try {
                int index = parsedInput.getIndex();
                String taskRemoved = taskList.get(index - 1).toString();
                taskList.deleteTask(index - 1);
                storage.saveFileData(taskList);
                return ui.printTaskDeletionMessage(taskRemoved, taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printInvalidIndexMessage(taskList);
            } catch (FileNotFoundException e) {
                return ui.printFileNotFoundMessage();
            }
        case "find":
            try {
                String keyword = parsedInput.getFindKeyword();
                return ui.printTasksWithKeyword(keyword, taskList);
            } catch (IndexOutOfBoundsException e) {
                return ui.printMissingKeywordMessage();
            }
        case "help":
        default:
            return ui.printOtherInputMessage();
        }
        return ui.printOtherInputMessage();
    }

}
