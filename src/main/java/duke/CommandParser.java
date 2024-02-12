package duke;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Processes user input according to available bot commands.
 */
public class CommandParser {
    private TaskList taskList;

    public CommandParser(Scanner scanner, TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Processes a single command.
     *
     * @param command input command
     * @return boolean indicating whether to continue processing
     */
    public boolean processCommand(String command) {
        try {
            String cmd = command.split(" ")[0];
            String params = command.substring(cmd.length()).trim();
            switch (cmd) {
                case "bye":
                    System.out.println("Au revoir! Till we meet again!");
                    return false;
                case "list":
                    parseList();
                    break;
                case "find":
                    parseFind(params);
                    break;
                case "mark":
                    parseMark(params);
                    break;
                case "unmark":
                    parseUnmark(params);
                    break;
                case "delete":
                    parseDelete(params);
                    break;
                case "todo":
                    parseTodo(params);
                    break;
                case "deadline":
                    parseDeadline(params);
                    break;
                case "event":
                    parseEvent(params);
                    break;
                case "undo":
                    parseUndo();
                    break;
                default:
                    throw new DukeBotException.UnknownCommandException();
            }
        } catch (DukeBotException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Oops! An error occurred");
        }
        return true;
    }

    private void parseList() {
        System.out.println("Here are the tasks:");
        taskList.printTasks();
    }

    private void parseUndo() {
        taskList.undo();
    }

    private void parseFind(String params) throws DukeBotException {
        if (params.length() == 0) {
            throw new DukeBotException.FindParamsException();
        }
        String keyword = params.trim();
        taskList.findTasks(keyword);
    }

    private void parseMark(String params) throws DukeBotException {
        if (params.length() == 0) {
            throw new DukeBotException.MarkParamsException();
        }
        int num = Integer.valueOf(params);
        taskList.markTask(num);
        Task task = taskList.getTask(num);

        System.out.println("Another one in the bag! Well done!");
        System.out.println(task);
    }

    private void parseUnmark(String params) throws DukeBotException {
        if (params.length() == 0) {
            throw new DukeBotException.MarkParamsException();
        }
        int num = Integer.valueOf(params);
        taskList.unmarkTask(num);
        Task task = taskList.getTask(num);

        System.out.println("Oh dear, better get on it!");
        System.out.println(task);
    }

    private void parseDelete(String params) throws DukeBotException {
        if (params.length() == 0) {
            throw new DukeBotException.DeleteParamsException();
        }
        int num = Integer.valueOf(params);
        Task toDelete = taskList.getTask(num);
        taskList.deleteTask(num);
        System.out.println("Task DELETE DELETE DELETE");
        System.out.println(toDelete);
    }

    private void parseTodo(String params) throws DukeBotException {
        Todo newTask;
        if (params.length() == 0) {
            throw new DukeBotException.TodoDescriptionMissingException();
        }

        String desc = params;

        newTask = new Todo(desc);
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    private void parseDeadline(String params) throws DukeBotException {
        DeadlineTask newTask;
        if (!params.contains("/by")) {
            throw new DukeBotException.DeadlineDetailsMissingException();
        }

        String desc = params.split("/by")[0].trim();
        String by = params.split("/by")[1].trim();

        // Check if by is in valid date format
        if (DateHandler.isValidInputDate(by)) {
            LocalDateTime dateObj = DateHandler.inputStrToDateTime(by);
            newTask = new DeadlineTask(desc, dateObj); // Create date object
        } else {
            newTask = new DeadlineTask(desc, by);
        }
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");

    }

    private void parseEvent(String params) throws DukeBotException {
        EventTask newTask;
        if (!params.contains("/from") || !params.contains("/to")) {
            throw new DukeBotException.EventDetailsMissingException();
        }

        String desc = params.split("/from")[0];
        String from = params.split("/from")[1].split("/to")[0].trim();
        String to = params.split("/to")[1].trim();

        if (DateHandler.isValidInputDate(from) && DateHandler.isValidInputDate(to)) {
            LocalDateTime dateObjFrom = DateHandler.inputStrToDateTime(from);
            LocalDateTime dateObjTo = DateHandler.inputStrToDateTime(to);
            newTask = new EventTask(desc, dateObjFrom, dateObjTo); // Create date object
        } else {
            newTask = new EventTask(desc, from, to);
        }
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

}
