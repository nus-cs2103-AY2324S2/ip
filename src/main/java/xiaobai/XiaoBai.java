
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
        Task newTask;
        String[] parts = input.split(" ");
        try {
            Command command = Parser.parseCommand(input);
            switch (command.getInputType()) {
                case BYE:
                    storage.writeArrayListToFile(taskList);
                    return ui.showExitMessage();
                case TODO:
                    newTask = new Todo(input.substring(5));
                    taskList.addTask(newTask);
                    return ui.showAddTaskMessage(newTask, taskList);
                case LIST:
                    return ui.showPrintListMessage(taskList);
                case DEADLINE:
                    parts = input.substring(9).split(" /");
                    newTask = new Deadline(parts[0], parts[1].substring(3));
                    taskList.addTask(newTask);
                    return ui.showAddTaskMessage(newTask, taskList);
                case EVENT:
                    parts = input.substring(6).split(" /");
                    newTask = new Event(parts[0], parts[1].substring(5),
                            parts[2].substring(3));
                    taskList.addTask(newTask);
                    return ui.showAddTaskMessage(newTask, taskList);
                case MARK:
                    int index = Integer.parseInt(parts[1]);
                    Task task = taskList.getTask(index - 1);
                    task.setDone();
                    return ui.showMarkMessage(task);
                case UNMARK:
                    Task unmarkTask = taskList.getTask(Integer.parseInt(parts[1]) - 1);
                    unmarkTask.setNotDone();
                    return ui.showUnmarkMessage(unmarkTask);
                case DELETE:
                    int deleteIndex = Integer.parseInt(parts[1]) - 1;
                    Task deletedTask = taskList.getTask(deleteIndex);
                    taskList.removeTask(deleteIndex);
                    return ui.showDeleteMessage(deletedTask, taskList);
                case FIND:
                    String stringToFind = input.substring(5);
                    return ui.showFoundTask(taskList, stringToFind);
                case UNKNOWN:
                    throw new XiaoBaiException("Unknown input");
                default:
                    throw new XiaoBaiException("Unknown input");
            }
        } catch (XiaoBaiException e) {
            return ui.showErrorMessage(e.toString());
        }
    }

    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN, FIND
    }
}