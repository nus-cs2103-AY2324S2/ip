package arona;

import arona.command.CommandType;

import arona.exception.AronaIncompleteCommandException;
import arona.exception.AronaInvalidCommandException;
import arona.exception.AronaInvalidDateException;
import arona.exception.AronaInvalidIndexException;

import java.io.IOException;
import java.util.Scanner;

import arona.parser.Parser;

import arona.storage.Storage;

import arona.task.Deadline;
import arona.task.Event;
import arona.task.Task;
import arona.task.TaskList;
import arona.task.ToDo;

import arona.ui.Ui;

public class Arona {
    private static final Storage storage = new Storage();
    private static final TaskList tasks = new TaskList();
    private static final Parser parser = new Parser();

    private void listTasks() {
        Ui.printLines(tasks.toString());
    }

    private void addToDo(String str) {
        ToDo task = new ToDo(str);
        tasks.addElements(task);

        Ui.printLines("I've added this task, Sensei!",
                "    " + task,
                "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".");
    }

    private void addDeadline(String str, String by) throws AronaInvalidDateException {
        Deadline deadline = new Deadline(str, by);
        tasks.addElements(deadline);
        Ui.printLines("I've added this deadline, Sensei!",
                "    " + deadline,
                "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".");
    }

    private void addEvent(String str, String start, String end) throws AronaInvalidDateException {
        Event event = new Event(str, start, end);
        tasks.addElements(event);
        Ui.printLines("I've added this event, Sensei!",
                "    " + event,
                "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".");
    }

    private void deleteTask(int id) {
        Task task = tasks.getTask(id);
        tasks.deleteElements(id);
        Ui.printLines("I've removed this task, Sensei!",
                "    " + task,
                "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".");
    }

    private void markDone(int id) {
        tasks.markIndexAsDone(id);
        Ui.printLines("I've marked this task as done, Sensei!",
                "    " + tasks.getTask(id));
    }

    private void markUndone(int id) {
        tasks.markIndexAsUndone(id);
        Ui.printLines("I've marked this task as not done, Sensei!",
                "    " + tasks.getTask(id));
    }

    private boolean processCommand(CommandType commandType, String[] commandSplit) throws AronaIncompleteCommandException, AronaInvalidIndexException, AronaInvalidCommandException, AronaInvalidDateException {
        if (commandType == CommandType.BYE) {
            return false;
        } else if (commandType == CommandType.LIST) {
            listTasks();
        } else if (commandType == CommandType.MARK) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (!tasks.checkIndexValidity(Integer.parseInt(commandSplit[1]))) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            markDone(index);
        } else if (commandType == CommandType.UNMARK) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (!tasks.checkIndexValidity(Integer.parseInt(commandSplit[1]))) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            markUndone(index);
        } else if (commandType == CommandType.TODO) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            addToDo(commandSplit[1]);
        } else if (commandType == CommandType.DEADLINE) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            String[] deadlineSplit = commandSplit[1].split(" /by ");

            if (deadlineSplit.length == 1 || deadlineSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("deadline time");
            }

            addDeadline(deadlineSplit[0], deadlineSplit[1]);
        } else if (commandType == CommandType.EVENT) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            String[] eventSplit = commandSplit[1].split(" /from ");

            if (eventSplit.length == 1 || eventSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("start time");
            }

            String[] eventSplitTime = eventSplit[1].split(" /to ");

            if (eventSplitTime.length == 1 || eventSplitTime[1].equals("")) {
                throw new AronaIncompleteCommandException("end time");
            }

            addEvent(eventSplit[0], eventSplitTime[0], eventSplitTime[1]);
        } else if(commandType == CommandType.DELETE) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (!tasks.checkIndexValidity(Integer.parseInt(commandSplit[1]))) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            deleteTask(index);
        } else {
            throw new AronaInvalidCommandException("");
        }
        return true;
    }

    public static void main(String[] args) {
        Arona arona = new Arona();
        Scanner scanner = new Scanner(System.in);
        String command;

        Ui.helloUser();

        try {
            storage.readTasksFromData(tasks);
        } catch (IOException e) {
            Ui.printLines("Sorry, Sensei! I seem to be struggling to load the tasks :(");
        }

        while (true) {
            try {
                command = scanner.nextLine();
                CommandType commandType = parser.parseInput(command);
                String[] inputs = command.split(" ", 2);
                if (!arona.processCommand(commandType, inputs)) {
                    break;
                }
                storage.writeTasks(tasks);
            } catch (Exception e) {
                Ui.printLines(e.getMessage());
            }
        }

        Ui.byeUser();
        storage.writeTasks(tasks);
        scanner.close();
    }
}
