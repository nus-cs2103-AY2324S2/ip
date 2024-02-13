package parser;

import java.util.ArrayList;

import exception.GeePeeTeeException;
import exception.InvalidDateException;
import exception.InvalidTaskFormatException;
import exception.InvalidTaskIndexException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;

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
    public String parseInput(String input) {
        String command = input.split(" ")[0];
        switch (command) {
            case "help":
                return ui.getListOfCommandsMessage();
            case "list":
                return taskList.printList();
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
            default:
                return ui.getErrorMessage("I'm sorry, but I don't know what that means :-(");
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
    private String processMarkCommand(String input) {
        try {
            if (input.trim().equals("mark")) {
                throw new InvalidTaskIndexException("The index of a task cannot be empty.");
            }
            int markIndex = Integer.parseInt(input.split(" ")[1]);
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
            return ui.getTaskMarkedMessage(taskToMark);
        } catch (GeePeeTeeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.getErrorMessage(e.getMessage());
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
    private String processUnmarkCommand(String input) {
        try {
            if (input.trim().equals("unmark")) {
                throw new InvalidTaskIndexException("The index of a task cannot be empty.");
            }
            int unmarkIndex = Integer.parseInt(input.split(" ")[1]);
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
            return ui.getTaskUnmarkedMessage(taskToUnmark);
        } catch (GeePeeTeeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.getErrorMessage(e.getMessage());
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
    private String processDeleteCommand(String input) {
        try {
            if (input.trim().equals("delete")) {
                throw new InvalidTaskIndexException("The index of a task cannot be empty.");
            }
            int deleteIndex = Integer.parseInt(input.split(" ")[1]);
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
            return ui.getDeleteTaskMessage(taskToDelete, taskList.getTaskCount());
        } catch (GeePeeTeeException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (InvalidTaskIndexException e) {
            return ui.getErrorMessage(e.getMessage());
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
    private String processEventCommand(String input) {
        try {
            Event newEvent = Event.createFromInput(input);
            taskList.addTask(newEvent);
            storage.saveTaskList(taskList.getTasksList());
            return ui.getAddTaskMessage(newEvent, taskList.getTaskCount());
        } catch (InvalidDateException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (InvalidTaskFormatException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (GeePeeTeeException e) {
            return ui.getErrorMessage(e.getMessage());
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
    private String processDeadlineCommand(String input) {
        try {
            Deadline newDeadline = Deadline.createFromInput(input);
            taskList.addTask(newDeadline);
            storage.saveTaskList(taskList.getTasksList());
            return ui.getAddTaskMessage(newDeadline, taskList.getTaskCount());
        } catch (InvalidDateException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (InvalidTaskFormatException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (GeePeeTeeException e) {
            return ui.getErrorMessage(e.getMessage());
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
    private String processToDoCommand(String input) {
        try {
            ToDo newToDo = ToDo.createFromInput(input);
            taskList.addTask(newToDo);
            storage.saveTaskList(taskList.getTasksList());
            return ui.getAddTaskMessage(newToDo, taskList.getTaskCount());
        } catch (InvalidTaskFormatException e) {
            return ui.getErrorMessage(e.getMessage());
        } catch (GeePeeTeeException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Processes the find command.
     * 
     * @param input The user input
     */
    private String processFindCommand(String input) {
        String keyword = input.split(" ")[1];
        ArrayList<Task> result = taskList.findTasks(keyword);
        return ui.getMatchingTasksMessage(result);
    }
}
