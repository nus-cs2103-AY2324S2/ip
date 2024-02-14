package duke;

import java.util.Scanner;

import duke.exceptions.HistoryIndexException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.PriorityLevel;
import duke.tasks.Task;
import duke.tasks.ToDo;

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
            + "Hello! I'm " + name + "\n"
            + "What can I do for you?\n"
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
            + "Bye. Hope to see you again soon!\n"
            + "_________________________\n";
        return finalPrint;
    }
    /**
     * Runs the UI that continuously takes in user input and output.
     *
     * @param currentInput The current user input.
     * @return The output to print.
     */
    public String run(String currentInput) {
        String[] currentInputSplit = currentInput.split(" ");
        Command currentCommand = null;
        PriorityLevel priority;
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
            int focusIndex = -1;
            try {
                focusIndex = parser.checkIndexGiven(currentInputSplit[1],
                history.getLength());
            } catch (HistoryIndexException e) {
                return "Invalid index selected!";
            }
            String output = history.deleteTask(focusIndex);
            return output;
        case MARK:
            focusIndex = -1;
            try {
                focusIndex = parser.checkIndexGiven(currentInputSplit[1],
                history.getLength());
            } catch (HistoryIndexException e) {
                return "Invalid index selected!";
            }
            output = history.markTask(focusIndex);
            return output;
        case UNMARK:
            focusIndex = -1;
            try {
                focusIndex = parser.checkIndexGiven(currentInputSplit[1],
                history.getLength());
            } catch (HistoryIndexException e) {
                return "Invalid index selected!";
            }
            output = history.unmarkTask(focusIndex);
            return output;
        case EVENT:
            Task event = null;
            String[] data;
            try {
                data = parser.extractDescriptionData(currentInputSplit);
                priority = Parser.getPriority(data[3]);
            } catch (InvalidInputException e) {
                return "That's not a valid input! " + e.getMessage();
            }
            try {
                event = new Event(data[0], parser.parseDate(data[1]), parser.parseDate(data[2]),
                    priority);
                output = history.addTask(event);
                return output;
            } catch (InvalidInputException e) {
                return "Invalid input: " + e.getMessage();
            }
        case TODO:
            event = null;
            try {
                data = parser.extractDescriptionData(currentInputSplit);
                priority = Parser.getPriority(data[3]);
            } catch (InvalidInputException e) {
                return "Invalid input: " + e.getMessage();
            }
            event = new ToDo(data[0], priority);
            output = history.addTask(event);
            return output;
        case DEADLINE:
            try {
                data = parser.extractDescriptionData(currentInputSplit);
                priority = Parser.getPriority(data[3]);
            } catch (InvalidInputException e) {
                return "Invalid input: " + e.getMessage();
            }
            try {
                event = new Deadline(data[0], parser.parseDate(data[1]), priority);
                output = history.addTask(event);
                return output;
            } catch (InvalidInputException e) {
                return "Invalid Input: " + e.getMessage();
            }
        case FIND:
            try {
                data = parser.extractDescriptionData(currentInputSplit);
            } catch (InvalidInputException e) {
                return "Invalid input: " + e.getMessage();
            }
            output = history.listKeywords(data[0]);
            return output;
        default:
            return "";
        }
    }
}
