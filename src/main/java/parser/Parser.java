package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import command.CommandResponse;
import exception.GeePeeTeeException;
import exception.InvalidDateException;
import exception.InvalidTaskFormatException;
import exception.InvalidTaskIndexException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Priority;
import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;
import utils.EnumConverter;

/**
 * Represents a parser that processes user input and executes the corresponding
 * commands.
 * <p>
 * This class is responsible for parsing user input and executing the
 * corresponding commands, such as adding, deleting, or marking tasks, as well
 * as
 * providing help and exiting the application.
 * </p>
 */
public class Parser {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new {@code Parser} instance with the specified task list,
     * storage
     * and user interface.
     * 
     * @param taskList The task list to be associated with the parser.
     * @param storage  The storage to be associated with the parser.
     * @param ui       The user interface to be associated with the parser.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses the user input and executes the corresponding command.
     * 
     * @param input The user input to be parsed and executed.
     */
    public CommandResponse parseInput(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "help":
                return CommandResponse.success(ui.getListOfCommandsMessage());
            case "list":
                return CommandResponse.success(taskList.printList());
            case "mark":
                return processMarkCommand(input);
            case "unmark":
                return processUnmarkCommand(input);
            case "delete":
                return processDeleteCommand(input);
            case "event":
                return processEventCommand(input);
            case "deadline":
                return processDeadlineCommand(input);
            case "todo":
                return processToDoCommand(input);
            case "find":
                return processFindCommand(input);
            case "tag":
                return processTagCommand(input);
            default:
                return CommandResponse.error(ui.getErrorMessage("I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Processes the mark command by attempting to mark the task at the specified
     * index.
     * 
     * @param input The user input to be processed.
     * @throws InvalidTaskIndexException If the index of the task is invalid or does
     *                                   not exist.
     * @throws GeePeeTeeException        If an error occurs while marking the task.
     */
    private CommandResponse processMarkCommand(String input) {
        try {
            if (input.trim().equals("mark")) {
                throw new InvalidTaskIndexException("The index of a task cannot be empty.");
            }
            int markIndex;
            try {
                markIndex = Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException("The index of a task must be a number.");
            }
            if (markIndex > taskList.getTaskCount()) {
                throw new InvalidTaskIndexException("The index of a task cannot be greater than the number of tasks.");
            }
            if (markIndex <= 0) {
                throw new InvalidTaskIndexException("The index of a task cannot be 0 or negative.");
            }
            Task taskToMark = taskList.getTask(markIndex);
            if (taskToMark == null) {
                throw new InvalidTaskIndexException("The task at index " + markIndex + " does not exist.");
            }
            taskToMark.markAsDone();
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getTaskMarkedMessage(taskToMark));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the unmark command by attempting to unmark the task at the
     * specified index.
     * 
     * @param input The user input to be processed.
     * @throws InvalidTaskIndexException If the index of the task is invalid or does
     *                                   not exist.
     * @throws GeePeeTeeException        If an error occurs while unmarking the
     *                                   task.
     */
    private CommandResponse processUnmarkCommand(String input) {
        try {
            if (input.trim().equals("unmark")) {
                throw new InvalidTaskIndexException("The index of a task cannot be empty.");
            }
            int unmarkIndex;
            try {
                unmarkIndex = Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException("The index of a task must be a number.");
            }
            if (unmarkIndex > taskList.getTaskCount()) {
                throw new InvalidTaskIndexException("The index of a task cannot be greater than the number of tasks.");
            }
            if (unmarkIndex <= 0) {
                throw new InvalidTaskIndexException("The index of a task cannot be 0 or negative.");
            }
            Task taskToUnmark = taskList.getTask(unmarkIndex);
            if (taskToUnmark == null) {
                throw new InvalidTaskIndexException("The task at index " + unmarkIndex + " does not exist.");
            }
            taskToUnmark.unmarkAsDone();
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getTaskUnmarkedMessage(taskToUnmark));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the delete command by attempting to delete the task at the
     * specified index.
     * 
     * @param input The user input to be processed.
     * @throws InvalidTaskIndexException If the index of the task is invalid or does
     *                                   not exist.
     * @throws GeePeeTeeException        If an error occurs while deleting the task.
     */
    private CommandResponse processDeleteCommand(String input) {
        try {
            if (input.trim().equals("delete")) {
                throw new InvalidTaskIndexException("The index of a task cannot be empty.");
            }
            int deleteIndex;
            try {
                deleteIndex = Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException("The index of a task must be a number.");
            }
            if (deleteIndex > taskList.getTaskCount()) {
                throw new InvalidTaskIndexException("The index of a task cannot be greater than the number of tasks.");
            }
            if (deleteIndex <= 0) {
                throw new InvalidTaskIndexException("The index of a task cannot be 0 or negative.");
            }
            Task taskToDelete = taskList.getTask(deleteIndex);
            if (taskToDelete == null) {
                throw new InvalidTaskIndexException("The task at index " + deleteIndex + " does not exist.");
            }
            taskList.removeTask(deleteIndex);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getDeleteTaskMessage(taskToDelete, taskList.getTaskCount()));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the event command by attempting to create and add a new event task
     * to the task list.
     * 
     * @param input The user input to be processed.
     * @throws InvalidDateException       If the date of the event is invalid.
     * @throws InvalidTaskFormatException If the input format of the event is
     *                                    incorrect.
     * @throws GeePeeTeeException         If an error occurs while adding the event
     *                                    task.
     */
    private CommandResponse processEventCommand(String input) {
        try {
            String[] parts = input.substring("event ".length()).split(" /from | /to ", -1);
            if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
                throw new InvalidTaskFormatException(
                        "Invalid event format. Please use 'event description /from yyyy-MM-dd /to yyyy-MM-dd'.");
            }

            String description = parts[0].trim();
            if (description.isEmpty()) {
                throw new InvalidTaskFormatException("The description of an event cannot be empty.");
            }
            String from = parts[1].trim();
            String to = parts[2].trim();

            LocalDate parsedFrom, parsedTo;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedFrom = LocalDate.parse(from, formatter);
            parsedTo = LocalDate.parse(to, formatter);
            if (parsedFrom.isBefore(LocalDate.now())) {
                throw new InvalidDateException("The start date of an event cannot be in the past.");
            }
            if (parsedTo.isBefore(LocalDate.now())) {
                throw new InvalidDateException("The end date of an event cannot be in the past.");
            }
            if (parsedTo.isBefore(parsedFrom)) {
                throw new InvalidDateException("The end date of an event cannot be before the start date.");
            }
            Event newEvent = new Event(description, parsedFrom, parsedTo);
            taskList.addTask(newEvent);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getAddTaskMessage(newEvent, taskList.getTaskCount()));
        } catch (InvalidDateException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the deadline command by attempting to create and add a new deadline
     * task to the task list.
     * 
     * @param input The user input to be processed.
     * @throws InvalidDateException       If the date of the deadline is invalid.
     * @throws InvalidTaskFormatException If the input format of the deadline is
     *                                    incorrect.
     * @throws GeePeeTeeException         If an error occurs while adding the
     *                                    deadline task.
     */
    private CommandResponse processDeadlineCommand(String input) {
        try {
            String[] parts = input.substring("deadline ".length()).split(" /by ", -1);
            if (parts.length != 2) {
                throw new InvalidTaskFormatException(
                        "Invalid deadline format. Please use 'deadline description /by yyyy-MM-dd'.");
            }
            String description = parts[0].trim();
            if (description.isEmpty()) {
                throw new InvalidTaskFormatException("The description of a deadline cannot be empty.");
            }
            String by = parts[1].trim();
            LocalDate parsedBy;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            parsedBy = LocalDate.parse(by, formatter);
            if (parsedBy.isBefore(LocalDate.now())) {
                throw new InvalidDateException("The date of a deadline cannot be in the past.");
            }
            Deadline newDeadline = new Deadline(description, parsedBy);
            taskList.addTask(newDeadline);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getAddTaskMessage(newDeadline, taskList.getTaskCount()));
        } catch (InvalidDateException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the todo command by attempting to create and add a new todo task to
     * the task list.
     * 
     * @param input The user input to be processed.
     * @throws InvalidTaskFormatException If the input format of the todo is
     *                                    incorrect.
     * @throws GeePeeTeeException         If an error occurs while adding the todo
     *                                    task.
     */
    private CommandResponse processToDoCommand(String input) {
        try {
            String description = input.substring("todo ".length()).trim();
            if (description.isEmpty()) {
                throw new InvalidTaskFormatException("The description of a todo cannot be empty.");
            }
            ToDo newToDo = new ToDo(description);
            taskList.addTask(newToDo);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getAddTaskMessage(newToDo, taskList.getTaskCount()));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the find command.
     * 
     * @param input The user input
     */
    private CommandResponse processFindCommand(String input) {
        try {
            if (input.trim().split(" ").length < 2) {
                throw new InvalidTaskFormatException("Please provide a keyword to find.");
            }
            String keyword = input.split(" ")[1];
            ArrayList<Task> result = taskList.findTasks(keyword);
            return CommandResponse.success(ui.getMatchingTasksMessage(result));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    /**
     * Processes the tag command to set priority to a task.
     * 
     * @param input The user input
     */
    private CommandResponse processTagCommand(String input) {
        try {
            if (input.trim().split(" ").length < 3) {
                throw new InvalidTaskFormatException("Please provide a task index and a priority to tag.");
            }
            int tagIndex;
            try {
                tagIndex = Integer.parseInt(input.split(" ")[1]);
            } catch (NumberFormatException e) {
                throw new InvalidTaskIndexException("The index of a task must be a number.");
            }
            if (tagIndex > taskList.getTaskCount()) {
                throw new InvalidTaskIndexException("The index of a task cannot be greater than the number of tasks.");
            }
            if (tagIndex <= 0) {
                throw new InvalidTaskIndexException("The index of a task cannot be 0 or negative.");
            }
            Task taskToTag = taskList.getTask(tagIndex);
            if (taskToTag == null) {
                throw new InvalidTaskIndexException("The task at index " + tagIndex + " does not exist.");
            }
            String priorityInput = input.split(" ")[2];
            Priority priority = EnumConverter.convertStringToPriority(priorityInput);
            taskToTag.setPriority(priority);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getTagTaskMessage(taskToTag));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }
}
