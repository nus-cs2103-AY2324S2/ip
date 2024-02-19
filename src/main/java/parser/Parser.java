package parser;

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
        switch (command.toLowerCase()) {
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
            int markIndex = parseTaskIndex(input.split(" ")[1]);
            Task taskToMark = taskList.getTask(markIndex);
            taskToMark.markAsDone();
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getTaskMarkedMessage(taskToMark));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("mark")));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("mark")));
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
            int unmarkIndex = parseTaskIndex(input.split(" ")[1]);
            Task taskToUnmark = taskList.getTask(unmarkIndex);
            taskToUnmark.unmarkAsDone();
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getTaskUnmarkedMessage(taskToUnmark));
        } catch (GeePeeTeeException e) {
            return CommandResponse
                    .error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("unmark")));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse
                    .error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("unmark")));
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
            int deleteIndex = parseTaskIndex(input.split(" ")[1]);
            Task taskToDelete = taskList.getTask(deleteIndex);
            taskList.removeTask(deleteIndex);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getDeleteTaskMessage(taskToDelete, taskList.getTaskCount()));
        } catch (GeePeeTeeException e) {
            return CommandResponse
                    .error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("delete")));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse
                    .error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("delete")));
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
            Event newEvent = EventParser.parseEvent(input);
            taskList.addTask(newEvent);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getAddTaskMessage(newEvent, taskList.getTaskCount()));
        } catch (InvalidDateException | InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("event")));
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
            Deadline newDeadline = DeadlineParser.parseDeadline(input);
            taskList.addTask(newDeadline);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getAddTaskMessage(newDeadline, taskList.getTaskCount()));
        } catch (InvalidDateException | InvalidTaskFormatException e) {
            return CommandResponse
                    .error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("deadline")));
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
            ToDo newToDo = ToDoParser.parseToDo(input);
            taskList.addTask(newToDo);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getAddTaskMessage(newToDo, taskList.getTaskCount()));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("todo")));
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
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("find")));
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
            int tagIndex = parseTaskIndex(input.split(" ")[1]);
            Task taskToTag = taskList.getTask(tagIndex);
            String priorityInput = input.split(" ")[2];
            Priority priority = EnumConverter.convertStringToPriority(priorityInput);
            taskToTag.setPriority(priority);
            storage.saveTaskList(taskList.getTasksList());
            return CommandResponse.success(ui.getTagTaskMessage(taskToTag));
        } catch (InvalidTaskFormatException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("tag")));
        } catch (InvalidTaskIndexException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage() + ui.getCommandDescriptionMessage("tag")));
        } catch (IllegalArgumentException e) {
            return CommandResponse.error(ui.getErrorMessage("Invalid priority. Please use 'high', 'medium' or 'low'."));
        } catch (GeePeeTeeException e) {
            return CommandResponse.error(ui.getErrorMessage(e.getMessage()));
        }
    }

    private int parseTaskIndex(String input) throws InvalidTaskIndexException {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException("The index of a task must be a number.");
        }
        return index;
    }
}
