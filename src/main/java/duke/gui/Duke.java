package duke.gui;

import duke.exception.EmptyTaskNameException;
import duke.exception.NoTaskTypeException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;

import duke.response.Response;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.IOException;

import java.time.format.DateTimeParseException;


/**
 * Handles the logic of GUI version of Duke.
 */
public class Duke {
    private static Response response;
    private static Storage storage;
    private static TaskList taskList;

    /**
     * Initializes DukeFX with the necessary components.
     *
     * @throws IOException If an I/O error occurs.
     */
    public  Duke() throws IOException {
        response = new Response();
        storage = new Storage();
        taskList = new TaskList();

        //load data from the file into the task list.
        storage.loadFile(taskList);
    }

    /**
     * Processes the user input and returns the appropriate response.
     *
     * @param input The user input.
     * @return The response to the user input.
     * @throws IOException If an I/O error occurs.
     */
    public String getResponse(String input) throws IOException {
        Parser parser = new Parser(input);
        String command = parser.getCommand();
        String task;
        String message;
        switch(command) {
        case "todo":
            try {
                parser.checkEmptyTask();
                task = taskList.addTask(new ToDo(parser.getTaskName(), false));
                message = response.getAddTaskMessage(task);
            } catch (EmptyTaskNameException e) {
                message = e.getMessage();
            }
            break;
        case "deadline":
            try {
                parser.checkEmptyTask();
                if (parser.getDue().split(" ").length == 2) {
                    task = taskList.addTask(
                            new Deadline(parser.getTaskName(), false, parser.parseDueTime()));
                } else {
                    task = taskList.addTask(new Deadline(
                            parser.getTaskName().trim(), false, parser.parseDueDate()));
                }
                message = response.getAddTaskMessage(task);
            } catch (EmptyTaskNameException e) {
                message = e.getMessage();
            } catch (DateTimeParseException e) {
                message = "Please specify the task's deadline using the format "
                        + "'yyyy-mm-dd hh:mm' or 'yyyy-mm-dd'";
            }
            break;
        case "event":
            try {
                parser.checkEmptyTask();
                task = taskList.addTask(
                        new Event(
                                parser.getTaskName(),
                                false,
                                parser.getEventStart(),
                                parser.getEventEnd()));
                message = response.getAddTaskMessage(task);
            } catch (EmptyTaskNameException e) {
                message = e.getMessage();
            }
            break;
        case "list":
            message = response.getListMessage(taskList);
            break;
        case "mark":
            message = response.getMarkMessage(taskList.markTask(parser.parseIndex()));
            break;
        case "unmark":
            message = response.getUnmarkMessage(taskList.unmarkTask(parser.parseIndex()));
            break;
        case "delete":
            message = response.getDeleteMessage(taskList.deleteTask(parser.parseIndex()), taskList.getSize());
            break;
        case "find":
            message = response.getFindMessage(taskList.find(parser.getUserInput()));
            break;
        case "tag":
            message = response.getTagMessage(taskList.tagTask(parser.parseIndex(), parser.getTag()));
            break;
        case "bye":
            storage.writeFile(taskList);
            message = response.getGoodbye();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> Platform.exit()));
            timeline.play();
            break;
        default:
            try {
                throw new NoTaskTypeException();
            } catch (NoTaskTypeException e) {
                message = e.getMessage();
            }
            break;
        }
        return message;
    }
}

