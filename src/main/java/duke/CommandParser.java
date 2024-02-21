package duke;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Processes user input according to available bot commands.
 */
public class CommandParser {
    private TaskList taskList;

    public CommandParser(Scanner scanner, TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Processes a command.
     *
     * @param command input command
     * @return boolean indicates if processing should continue
     */

    public boolean processCommand(String command) {
        try {
            String cmd = command.split(" ")[0];
            String para = command.substring(cmd.length()).trim();
            switch(cmd) {
            case "bye":
                System.out.println("Au revoir! Till we meet again!");
                return false;
            case "list":
                parseList();
                break;
            case "find":
                parseFind(para);
                break;
            case "mark":
                parseMark(para);
                break;
            case "unmark":
                parseUnmark(para);
                break;
            case "delete":
                parseDelete(para);
                break;
            case "todo":
                parseTodo(para);
                break;
            case "deadline":
                parseDeadline(para);
                break;
            case "event":
                parseEvent(para);
                break;
            case "undo":
                parseUndo();
                break;
            default:
                throw new DukeBotException.UnknownException();
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

    private void parseFind(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.FindException();
        }
        String searchword = para.trim();
        taskList.findTasks(searchword);
    }

    private void parseMark(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.MarkException();
        }
        int index = Integer.valueOf(para);
        taskList.markTask(index);
        Task task = taskList.getTask(index);

        System.out.println("Another one in the bag! Well done!");
        System.out.println(task);
    }

    private void parseUnmark(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.MarkException();
        }
        int index = Integer.valueOf(para);
        taskList.unmarkTask(index);
        Task task = taskList.getTask(index);

        System.out.println("Oh dear, better get on it!");
        System.out.println(task);
    }

    private void parseDelete(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.DeleteException();
        }
        int index = Integer.valueOf(para);
        Task toDelete = taskList.getTask(index);
        taskList.deleteTask(index);

        System.out.println("Task DELETE DELETE DELETE");
        System.out.println(toDelete);
    }

    private void parseTodo(String para) throws DukeBotException {
        Todo newTask;
        if (para.length() == 0) {
            throw new DukeBotException.TodoException();
        }

        String description = para;

        newTask = new Todo(description);
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    private void parseDeadline(String params) throws DukeBotException {
        DeadlineTask newTask;
        if (!params.contains("/by")) {
            throw new DukeBotException.DeadlineException();
        }

        String description = params.split("/by")[0].trim();
        String by = params.split("/by")[1].trim();

        // Check if by is in valid date format
        if (DateHandler.isValidInputDate(by)) {
            LocalDateTime dateObj = DateHandler.inputStringDateTime(by);
            newTask = new DeadlineTask(description, dateObj); // Create date object
        } else {
            newTask = new DeadlineTask(description, by);
        }
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");

    }

    private void parseEvent(String para) throws DukeBotException {
        EventTask newTask;
        if (!para.contains("/from") || !para.contains("/to")) {
            throw new DukeBotException.EventException();
        }

        String description = para.split("/from")[0];
        String from = para.split("/from")[1].split("/to")[0].trim();
        String to = para.split("/to")[1].trim();

        if (DateHandler.isValidInputDate(from) && DateHandler.isValidInputDate(to)) {
            LocalDateTime dateObjFrom = DateHandler.inputStringDateTime(from);
            LocalDateTime dateObjTo = DateHandler.inputStringDateTime(to);
            newTask = new EventTask(description, dateObjFrom, dateObjTo); // Create date object
        } else {
            newTask = new EventTask(description, from, to);
        }
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

}
