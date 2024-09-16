package shuheng;

import java.time.LocalDateTime;
import java.util.Scanner;

import shuheng.exceptions.HistoryIndexException;
import shuheng.exceptions.InvalidInputException;
import shuheng.exceptions.InvalidTaskException;
import shuheng.tasks.Deadline;
import shuheng.tasks.Event;
import shuheng.tasks.PriorityLevel;
import shuheng.tasks.Task;
import shuheng.tasks.ToDo;

/**
 * This class represents the user-interface, that reads and write i/o to the user.
 */
public class Ui {

    private Storage manager;
    private Parser parser;
    private TaskList history;
    private Scanner sc;

    /**
     * This enum represents a UI command.
     */
    public enum Command {
        BYE,
        LIST,
        EVENT,
        TODO,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE,
        FIND,
    }

    /**
     * This is a constructor for our user-interface.
     * @param manager Manages storage and logging for our to-do list.
     * @param parser Parses and handles user input.
     * @param history The task list representing the history of what we have added.
     */
    public Ui(Storage manager, Parser parser, TaskList history) {
        this.history = history;
        this.manager = manager;
        this.parser = parser;
        this.sc = new Scanner(System.in);
        sayHi();
    }
    /**
     * Lets shuheng say hi to everyone! :)
     *
     * @return The output to print.
     */
    public static String sayHi() {
        String name = "shu heng";
        String nameDisplay = "_________________________\n"
            + "Hello! I'm " + name + ".\n"
            + "What do you want.\n"
            + "_________________________\n";
        return nameDisplay;
    }

    /**
     * Lets shuheng say bye to everyone! :)
     *
     * @return The output to print.
     */
    public String sayBye() {
        String finalPrint = "_________________________\n"
            + "Bye. Please don't come back.\n"
            + "_________________________\n";
        return finalPrint;
    }

    private String handleDelete(String[] currentInputSplit) {
        int focusIndex = -1;
        try {
            focusIndex = parser.checkIndexGiven(currentInputSplit[1],
              history.getLength());
        } catch (HistoryIndexException e) {
            return "Invalid index selected!";
        }
        String output = history.deleteTask(focusIndex);
        return output;
    }

    private String handleMark(String[] currentInputSplit) {
        int focusIndex = -1;
        try {
            focusIndex = parser.checkIndexGiven(currentInputSplit[1],
              history.getLength());
        } catch (HistoryIndexException e) {
            return "Invalid index selected!";
        }
        return history.markTask(focusIndex);
    }

    private String handleUnmark(String[] currentInputSplit) {
        int focusIndex = -1;
        try {
            focusIndex = parser.checkIndexGiven(currentInputSplit[1],
              history.getLength());
        } catch (HistoryIndexException e) {
            return "Invalid index selected!";
        }
        return history.unmarkTask(focusIndex);
    }

    private String handleEvent(String[] currentInputSplit) {
        Task event = null;
        PriorityLevel priority;
        String[] data;
        try {
            data = parser.extractDescriptionData(currentInputSplit);
            priority = Parser.getPriority(data[3]);
        } catch (InvalidInputException e) {
            return "That's not a valid input! " + e.getMessage();
        }
        try {
            LocalDateTime startDate = parser.parseDate(data[1]);
            LocalDateTime endDate = parser.parseDate(data[2]);
            assert (startDate.compareTo(endDate) <= 0);
            event = new Event(data[0], startDate, endDate,
              priority);
            return history.addTask(event);
        } catch (InvalidInputException e) {
            return "Invalid input: " + e.getMessage();
        }
    }

    private String handleTodo(String[] currentInputSplit) {
        Task event = null;
        PriorityLevel priority;
        String[] data;
        try {
            data = parser.extractDescriptionData(currentInputSplit);
            priority = Parser.getPriority(data[3]);
        } catch (InvalidInputException e) {
            return "Invalid input: " + e.getMessage();
        }
        event = new ToDo(data[0], priority);
        return history.addTask(event);
    }

    private String handleDeadline(String[] currentInputSplit) {
        Task event = null;
        PriorityLevel priority;
        String[] data;
        try {
            data = parser.extractDescriptionData(currentInputSplit);
            priority = Parser.getPriority(data[3]);
        } catch (InvalidInputException e) {
            return "Invalid input: " + e.getMessage();
        }
        try {
            event = new Deadline(data[0], parser.parseDate(data[1]), priority);
            return history.addTask(event);
        } catch (InvalidInputException e) {
            return "Invalid Input: " + e.getMessage();
        }
    }

    private String handleFind(String[] currentInputSplit) {
        String[] data;
        try {
            data = parser.extractDescriptionData(currentInputSplit);
        } catch (InvalidInputException e) {
            return "Invalid input: " + e.getMessage();
        }
        return history.listKeywords(data[0]);
    }

    /**
     * Runs the UI that continuously takes in user input and output.
     *
     * @param currentInput The current user input.
     * @return The output to print.
     */
    public String run(String currentInput) {
        String[] currentInputSplit = currentInput.split(" ");
        Command currentCommand;
        try {
            currentCommand = parser.getCommand(currentInputSplit);
        } catch (InvalidTaskException e) {
            return "That's not a valid input! " + e.getMessage();
        }
        switch (currentCommand) {
        case LIST:
            return history.showTaskList();
        case BYE:
            return this.sayBye();
        case DELETE:
            return handleDelete(currentInputSplit);
        case MARK:
            return handleMark(currentInputSplit);
        case UNMARK:
            return handleUnmark(currentInputSplit);
        case EVENT:
            return handleEvent(currentInputSplit);
        case TODO:
            return handleTodo(currentInputSplit);
        case DEADLINE:
            return handleDeadline(currentInputSplit);
        case FIND:
            return handleFind(currentInputSplit);
        default:
            return "";
        }
    }
}
