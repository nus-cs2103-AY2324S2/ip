package duke;

import java.time.LocalDateTime;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Processes user input (user commands)
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
            String commands = command.split(" ")[0];
            String para = command.substring(commands.length()).trim();
            switch(commands) {
            case "list":
                cmdList();
                break;
            case "todo":
                cmdTodo(para);
                break;
            case "deadline":
                cmdDeadline(para);
                break;
            case "event":
                cmdEvent(para);
                break;
            case "find":
                cmdFind(para);
                break;
            case "mark":
                cmdMark(para);
                break;
            case "unmark":
                cmdUnmark(para);
                break;
            case "delete":
                cmdDelete(para);
                break;
            case "undo":
                cmdUndo();
                break;
            case "bye":
                cmdExit();
                return false;
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

    private void cmdList() {
        System.out.println("Here are the tasks:");
        taskList.printTasks();
    }

    private void cmdMark(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.MarkException();
        }

        int index = Integer.valueOf(para);
        taskList.markTask(index);
        Task task = taskList.getTask(index);

        System.out.println("Another one in the bag! Well done!");
        System.out.println(task);
    }

    private void cmdUnmark(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.MarkException();
        }

        int index = Integer.valueOf(para);
        taskList.unmarkTask(index);
        Task task = taskList.getTask(index);

        System.out.println("Oh dear, better get on it!");
        System.out.println(task);
    }

    private void cmdDelete(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.DeleteException();
        }

        int index = Integer.valueOf(para);
        Task toDelete = taskList.getTask(index);
        taskList.deleteTask(index);

        System.out.println("Task DELETE DELETE DELETE");
        System.out.println(toDelete);
    }

    private void cmdUndo() {
        taskList.undo();
    }

    private void cmdFind(String para) throws DukeBotException {
        if (para.length() == 0) {
            throw new DukeBotException.FindException();
        }
        String searchword = para.trim();
        taskList.findTasks(searchword);
    }

    /**
     * Creates a delay that allows user to see the exit message of the application
     * @param response
     */
    private void cmdExit() {
        System.out.println(" Au revoir! Till we meet again!");
        PauseTransition exitDisplay = new PauseTransition(Duration.seconds(1));
        exitDisplay.setOnFinished(event -> Platform.exit());
        exitDisplay.play();
    }

    private void cmdTodo(String para) throws DukeBotException {
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

    private void cmdDeadline(String params) throws DukeBotException {
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

    private void cmdEvent(String para) throws DukeBotException {
        EventTask newTask;
        if (!para.contains("/from") || !para.contains("/to")) {
            throw new DukeBotException.EventException();
        }

        String description = para.split("/from")[0];
        String from = para.split("/from")[1].split("/to")[0].trim();
        String to = para.split("/to")[1].trim();

        if (DateHandler.isValidInputDate(from) && DateHandler.isValidInputDate(to)) {
            LocalDateTime frDate = DateHandler.inputStringDateTime(from);
            LocalDateTime toDate = DateHandler.inputStringDateTime(to);
            newTask = new EventTask(description, frDate, toDate); // Create date object
        } else {
            newTask = new EventTask(description, from, to);
        }
        taskList.addTask(newTask);

        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

}
