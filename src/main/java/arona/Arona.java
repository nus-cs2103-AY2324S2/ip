package arona;

import arona.command.CommandType;

import arona.exception.*;

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

    private static String listTasks() {
        return tasks.toString();
    }

    private static String addToDo(String str) {
        ToDo task = new ToDo(str);
        tasks.addElements(task);
        assert tasks.getSize() > 0 : "The task list should not be empty";

        return "I've added this task, Sensei!" + "\n"
                + "    " + task + "\n"
                + "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".";
    }

    private static String addDeadline(String str, String by) throws AronaInvalidDateException {
        Deadline deadline = new Deadline(str, by);
        tasks.addElements(deadline);
        assert tasks.getSize() > 0 : "The task list should not be empty";

        return "I've added this deadline, Sensei!" + "\n"
                + "    " + deadline + "\n"
                + "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".";
    }

    private static String addEvent(String str, String start, String end) throws AronaInvalidDateException {
        Event event = new Event(str, start, end);
        tasks.addElements(event);
        assert tasks.getSize() > 0 : "The task list should not be empty";

        return "I've added this event, Sensei!" + "\n"
                + "    " + event + "\n"
                + "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".";
    }

    private static String deleteTask(int id) {
        Task task = tasks.getTask(id);
        tasks.deleteElements(id);
        return "I've removed this task, Sensei!" + "\n"
                + "    " + task + "\n"
                + "Now, your task list has " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? "" : "s") + ".";
    }

    private static String markDone(int id) {
        tasks.markIndexAsDone(id);
        return "I've marked this task as done, Sensei!" + "\n"
                + "    " + tasks.getTask(id);
    }

    private static String markUndone(int id) {
        tasks.markIndexAsUndone(id);
        return "I've marked this task as not done, Sensei!" + "\n"
                + "    " + tasks.getTask(id);
    }

    private static String findTasks(String keyword) {
        return tasks.listTasksWithKeyword(keyword);
    }

    public static String enterArona() {
        return "Welcome, sensei! Arona has been waiting for you.";
    }

    public static String exitArona() {
        return "Thanks for the hard work, Sensei!";
    }

    /**
     * Runs the command from the user input.
     *
     * @param commandType Type of command of the user input.
     * @param commandSplit Arguments of the input.
     * @return Response message to be sent by the bot.
     */
    private static String processCommand(CommandType commandType, String[] commandSplit)
            throws AronaException {
        String response = "";
        if (commandType == CommandType.BYE) {
            response = exitArona();
        } else if (commandType == CommandType.LIST) {
            response = listTasks();
        } else if (commandType == CommandType.MARK) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (!tasks.checkIndexValidity(Integer.parseInt(commandSplit[1]))) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            response = markDone(index);
        } else if (commandType == CommandType.UNMARK) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (!tasks.checkIndexValidity(Integer.parseInt(commandSplit[1]))) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            response = markUndone(index);
        } else if (commandType == CommandType.TODO) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            response = addToDo(commandSplit[1]);
        } else if (commandType == CommandType.DEADLINE) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            String[] deadlineSplit = commandSplit[1].split(" /by ");

            if (deadlineSplit.length == 1 || deadlineSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("deadline time");
            }

            response = addDeadline(deadlineSplit[0], deadlineSplit[1]);
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

            response = addEvent(eventSplit[0], eventSplitTime[0], eventSplitTime[1]);
        } else if(commandType == CommandType.DELETE) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (!tasks.checkIndexValidity(Integer.parseInt(commandSplit[1]))) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.getSize()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            response = deleteTask(index);
        } else if (commandType == CommandType.FIND) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("keyword");
            }

            response = findTasks(commandSplit[1]);
        } else {
            throw new AronaInvalidCommandException("");
        }
        return response;
    }

    /**
     * Gets all tasks from the data and store it in the storage.
     */
    public void loadTaskList() {
        try {
            storage.loadTaskListFromStorage(tasks);
        } catch (IOException exception) {
            return;
        }
    }

    /**
     * Gets the response message based on the User's input.
     *
     * @param userInput User input.
     * @return Response message to be sent by the bot.
     */
    public String getResponse(String userInput) {
        String response = "";

        try {
            CommandType command = parser.parseInput(userInput);
            String[] inputs = userInput.split(" ", 2);

            response = processCommand(command, inputs);
            storage.saveTaskListToStorage(tasks);
        } catch (AronaException exception) {
            response = Ui.getLines(exception.getMessage());
        }

        return response;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        try {
            storage.loadTaskListFromStorage(tasks);
        } catch (IOException e) {
            Ui.printLines("Sorry, Sensei! I seem to be struggling to load the tasks :(");
            return;
        }

        while (true) {
            try {
                command = scanner.nextLine();
                CommandType commandType = parser.parseInput(command);
                String[] inputs = command.split(" ", 2);
                if (Arona.processCommand(commandType, inputs) == exitArona()) {
                    break;
                }
                storage.saveTaskListToStorage(tasks);
            } catch (Exception e) {
                Ui.printLines(e.getMessage());
            }
        }

        scanner.close();
    }
}
