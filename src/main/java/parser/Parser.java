package parser;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;
import exception.DukeException;
import exception.InvalidDateException;
import exception.InvalidTaskFormatException;
import exception.InvalidTaskIndexException;

public class Parser {
  private TaskList taskList;
  private Storage storage;
  private Ui ui;

  public Parser(TaskList taskList, Storage storage, Ui ui) {
    this.taskList = taskList;
    this.storage = storage;
    this.ui = ui;
  }

  public void parseInput(String input) {
    String command = input.split(" ")[0];
    switch (command) {
      case "help":
        ui.showListOfCommands();
        break;
      case "bye":
        break;
      case "list":
        taskList.printList();
        break;
      case "mark":
        processMarkCommand(input);
        break;
      case "unmark":
        processUnmarkCommand(input);
        break;
      case "delete":
        processDeleteCommand(input);
        break;
      case "event":
        processEventCommand(input);
        break;
      case "deadline":
        processDeadlineCommand(input);
        break;
      case "todo":
        processToDoCommand(input);
        break;
      default:
        ui.showErrorMessage("I'm sorry, but I don't know what that means :-(");
    }
  }

  /*
   * Processes the mark command
   */
  private void processMarkCommand(String input) {
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
      ui.showTaskMarked(taskToMark);
      storage.saveTaskList(taskList.getTasksList());
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (InvalidTaskIndexException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }

  /*
   * Processes the unmark command
   */
  private void processUnmarkCommand(String input) {
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
      ui.showTaskUnmarked(taskToUnmark);
      storage.saveTaskList(taskList.getTasksList());
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (InvalidTaskIndexException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }

  /*
   * Processes the delete command
   */
  private void processDeleteCommand(String input) {
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
      ui.showDeleteTask(taskToDelete, taskList.getTaskCount());
      storage.saveTaskList(taskList.getTasksList());
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (InvalidTaskIndexException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }

  /*
   * Processes the event command
   */
  private void processEventCommand(String input) {
    try {
      Event newEvent = Event.createFromInput(input);
      taskList.addTask(newEvent);
      ui.showAddTask(newEvent, taskList.getTaskCount());
      storage.saveTaskList(taskList.getTasksList());
    } catch (InvalidDateException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (InvalidTaskFormatException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }

  /*
   * Processes the deadline command
   */
  private void processDeadlineCommand(String input) {
    try {
      Deadline newDeadline = Deadline.createFromInput(input);
      taskList.addTask(newDeadline);
      ui.showAddTask(newDeadline, taskList.getTaskCount());
      storage.saveTaskList(taskList.getTasksList());
    } catch (InvalidDateException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (InvalidTaskFormatException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }

  /*
   * Processes the todo command
   */
  private void processToDoCommand(String input) {
    try {
      ToDo newToDo = ToDo.createFromInput(input);
      taskList.addTask(newToDo);
      ui.showAddTask(newToDo, taskList.getTaskCount());
      storage.saveTaskList(taskList.getTasksList());
    } catch (InvalidTaskFormatException e) {
      ui.showErrorMessage(e.getMessage());
    } catch (DukeException e) {
      ui.showErrorMessage(e.getMessage());
    }
  }
}
