
package xiaobai;

import ui.Ui;

import storage.Storage;

import task.TaskList;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import exception.XiaoBaiException;

import command.Command;

import parser.Parser;

public class XiaoBai {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private static final String BOTNAME = "XiaoBai";

    public XiaoBai() {
        this.ui = new Ui();
        this.storage = new Storage("./xiaobai.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (XiaoBaiException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        }
    }

    public static String getName() {
        return BOTNAME;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String[] parts = input.split(" ");
        try {
            Command command = Parser.parseCommand(input);
            switch (command.getInputType()) {
                case BYE:
                    storage.writeArrayListToFile(taskList);
                    return ui.showExitMessage();
                case TODO:
                    assert input.substring(4) != null : "Task Details cannot be empty";
                    Todo newTodo = new Todo(input.substring(4));
                    taskList.addTask(newTodo);
                    return ui.showAddTaskMessage(newTodo, taskList);
                case LIST:
                    return ui.showPrintListMessage(taskList);
                case DEADLINE:
                    assert input.substring(8) != null : "Task Details cannot be empty";
                    parts = input.substring(8).split(" /");
                    Deadline newDeadline = new Deadline(parts[0], parts[1].substring(3));
                    taskList.addTask(newDeadline);
                    return ui.showAddTaskMessage(newDeadline, taskList);
                case EVENT:
                    assert input.substring(5) != null : "Task Details cannot be empty";
                    parts = input.substring(5).split(" /");
                    Event newEvent = new Event(parts[0], parts[1].substring(5),
                            parts[2].substring(3));
                    taskList.addTask(newEvent);
                    return ui.showAddTaskMessage(newEvent, taskList);
                case MARK:
                    assert parts[1] != null : "Mark details cannot be empty";
                    int index = Integer.parseInt(parts[1]);
                    Task task = taskList.getTask(index - 1);
                    task.setDone();
                    return ui.showMarkMessage(task);
                case UNMARK:
                    assert parts[1] != null : "Unmark details cannot be empty";
                    Task unmarkTask = taskList.getTask(Integer.parseInt(parts[1]) - 1);
                    unmarkTask.setNotDone();
                    return ui.showUnmarkMessage(unmarkTask);
                case DELETE:
                    assert parts[1] != null : "Delete details cannot be empty";
                    int deleteIndex = Integer.parseInt(parts[1]) - 1;
                    Task deletedTask = taskList.getTask(deleteIndex);
                    taskList.removeTask(deleteIndex);
                    return ui.showDeleteMessage(deletedTask, taskList);
                case FIND:
                    String stringToFind = input.substring(5);
                    assert stringToFind != null : "Find details cannot be empty";
                    return ui.showFoundTask(taskList, stringToFind);
                case UNKNOWN:
                    throw new XiaoBaiException("Unknown input");
                default:
                    throw new XiaoBaiException("Unknown input");
            }
        } catch (XiaoBaiException e) {
            return ui.showErrorMessage(e);
        }
    }

    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN, FIND
    }
}