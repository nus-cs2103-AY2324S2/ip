package duke;

import ui.Ui;

import storage.Storage;

import task.TaskList;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import exception.DukeException;

import command.Command;

import parser.Parser;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private String botName = "Yube";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Main method.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./yube.txt").run();
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcomeMessage(botName);
        boolean hasEnded = true;
        while (hasEnded) {
            Task newTask;
            String[] parts;
            try {
                String input = ui.readLine();
                Command command = Parser.parseCommand(input);
                switch (command.getInputType()) {
                    case BYE:
                        ui.showExitMessage();
                        storage.writeArrayListToFile(taskList);
                        hasEnded = false;
                        break;
                    case TODO:
                        newTask = new Todo(input.substring(5));
                        taskList.addTask(newTask);
                        ui.showRepeatFunction(newTask, taskList);
                        break;
                    case LIST:
                        ui.printList(taskList);
                        break;
                    case DEADLINE:
                        parts = input.substring(9).split(" /");
                        newTask = new Deadline(parts[0], parts[1].substring(3));
                        taskList.addTask(newTask);
                        ui.showRepeatFunction(newTask, taskList);
                        break;
                    case EVENT:
                        parts = input.substring(6).split(" /");
                        newTask = new Event(parts[0], parts[1].substring(5),
                                parts[2].substring(3));
                        taskList.addTask(newTask);
                        ui.showRepeatFunction(newTask, taskList);
                        break;
                    case MARK:
                        parts = input.split(" ");
                        int index = Integer.parseInt(parts[1]);
                        Task task = taskList.getTask(index - 1);
                        task.setDone();
                        ui.showMark(task);
                        break;
                    case UNMARK:
                        parts = input.split(" ");
                        Task unmarkTask = taskList.getTask(Integer.parseInt(parts[1]) - 1);
                        unmarkTask.setNotDone();
                        ui.showUnmark(unmarkTask);
                        break;
                    case DELETE:
                        parts = input.split(" ");
                        int deleteIndex = Integer.parseInt(parts[1]) - 1;
                        Task deletedTask = taskList.getTask(deleteIndex);
                        taskList.removeTask(deleteIndex);
                        ui.deleteTask(deletedTask, taskList);
                        break;
                    case UNKNOWN:
                        throw new DukeException("Unknown input");
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e.toString());
                hasEnded = false;
                break;
            }
        }
    }

    /**
     * Enum representing the types of commands Duke can handle.
     */
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }
}
