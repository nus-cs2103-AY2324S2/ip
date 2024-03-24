package squid;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import squid.constants.CorrectUsage;
import squid.constants.Exceptions;
import squid.constants.Help;
import squid.constants.Messages;
import squid.constants.Regex;
import squid.exceptions.DuplicateTaskNameException;
import squid.exceptions.IncorrectIndexException;
import squid.exceptions.IncorrectInputException;
import squid.exceptions.NotEnoughDatesException;
import squid.exceptions.NotEnoughInputsException;
import squid.exceptions.ParseFailException;
import squid.exceptions.SquidDateException;
import squid.exceptions.SquidException;
import squid.tasks.DateTime;
import squid.tasks.Deadline;
import squid.tasks.Event;
import squid.tasks.Task;
import squid.tasks.Tasks;
import squid.tasks.Todo;
import squid.ui.DialogBox;

/**
 * The main class of Squid.
 */
public class Squid {

    public Squid() {
        new Tasks();
    }

    /**
     * List all existing tasks.
     *
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws IncorrectIndexException Should never happen.
     */
    private static String list() throws NotEnoughInputsException, IncorrectIndexException {
        StringBuilder res = new StringBuilder();
        res.append(echo(Messages.LIST));
        res.append(Tasks.list());
        return res.toString();
    }

    /**
     * Initializes Squid.
     */
    public static String hello() {
        try {
            Tasks.read();
        } catch (ParseFailException | DuplicateTaskNameException | SquidDateException e) {
            echo(e.toString());
        }
        return Messages.LINE_BREAK + "\n" + Messages.HELLO + "\n" + Messages.LINE_BREAK;
    }

    /**
     * Terminates the program.
     *
     * @throws NotEnoughInputsException Should never happen, unless constant MESSAGES.BYE is blank.
     */
    private static String bye() throws NotEnoughInputsException {
        Tasks.save();
        return Messages.BYE;
    }

    /**
     * Overloaded method to include this as a valid command.
     *
     * @param message The raw input from the user, or message to be printed.
     * @param isFromUser Whether to further process message.
     * @throws NotEnoughInputsException If there are not enough parameters.
     */
    private static String echo(String message, boolean isFromUser) throws NotEnoughInputsException {
        if (!isFromUser) {
            return echo(message);
        }
        String[] params = message.split(" ", 2);
        if (params.length <= 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            Exceptions.NOT_ENOUGH_INPUTS, "echo", CorrectUsage.ECHO));
        }
        return echo(params[1]);
    }

    /**
     * Prints a message with a custom header.
     *
     * @param message The message to be printed.
     */
    private static String echo(String message) {
        return Messages.ECHO + message;
    }

    /**
     * Handles the creation of a todo task.
     *
     * @param message The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws DuplicateTaskNameException If there is an existing task with the same name.
     */
    private static String todo(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            Exceptions.NOT_ENOUGH_INPUTS, "todo", CorrectUsage.TODO));

        }

        Task t = new Todo(params[1]);
        Tasks.add(t);
        return echo(String.format(Messages.TODO, t));
    }

    /**
     * Handles the creation of a deadline task.
     *
     * @param message The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws DuplicateTaskNameException If there is an existing task with the same name.
     * @throws SquidDateException If the date given is unable to be parsed.
     */
    private static String deadline(String message) throws
            NotEnoughInputsException,
            DuplicateTaskNameException,
            SquidDateException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            Exceptions.NOT_ENOUGH_INPUTS,
                            "deadline",
                            CorrectUsage.DEADLINE));
        }
        String[] arguments = params[1].split(Regex.DEADLINE);

        if (arguments.length == 1) {
            throw new NotEnoughDatesException(
                    String.format(
                            Exceptions.NOT_ENOUGH_DATES,
                            1,
                            "deadline",
                            CorrectUsage.DEADLINE));
        }
        String task = arguments[0];
        DateTime dateTime = new DateTime(arguments[1]);
        Task t = new Deadline(task, dateTime);
        Tasks.add(t);
        return echo(String.format(Messages.DEADLINE, t));
    }

    /**
     * Handles the creation of an event task.
     *
     * @param message The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws DuplicateTaskNameException If there is an existing task with the same name.
     * @throws SquidDateException If the date given is unable to be parsed.
     */
    private static String event(String message) throws
            NotEnoughInputsException,
            DuplicateTaskNameException,
            SquidDateException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            Exceptions.NOT_ENOUGH_INPUTS,
                            "event",
                            CorrectUsage.EVENT));
        }
        params = params[1].split(Regex.EVENT_FROM);
        if (params.length != 2 || params[1].split(Regex.EVENT_TO).length != 2) {
            throw new NotEnoughDatesException(
                    String.format(
                            Exceptions.NOT_ENOUGH_DATES,
                            2,
                            "event",
                            CorrectUsage.EVENT));
        }
        String[] dates = params[1].split(Regex.EVENT_TO);
        DateTime from = new DateTime(dates[0]);
        DateTime to = new DateTime(dates[1]);
        Task t = new Event(params[0], from, to);
        Tasks.add(t);
        return echo(String.format(Messages.EVENT, t));
    }


    /**
     * Mark a task as either complete or incomplete.
     *
     * @param input The user's raw input.
     * @param isCompleted Whether to mark it completed or incomplete.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws IncorrectIndexException If the index does not refer to a valid task.
     */
    private static String mark(String input, boolean isCompleted) throws
            NotEnoughInputsException,
            IncorrectIndexException {
        String[] params = input.split(" ", 2);
        if (params.length <= 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            Exceptions.NOT_ENOUGH_INPUTS,
                            isCompleted ? "mark" : "unmark",
                            CorrectUsage.mark(isCompleted)));
        }
        String task = params[1];
        // Find the task entry.
        Task found = null;
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).getTaskName().equals(task)) {
                found = Tasks.get(i);
            }
        }
        if (found != null) {
            if (found.isCompleted() == isCompleted) {
                return echo(String.format(Messages.MARK_REPEAT, isCompleted ? "" : "un"));
            } else {
                found.setCompleted(isCompleted);
                return echo(String.format(isCompleted
                        ? Messages.MARK_COMPLETE
                        : Messages.MARK_INCOMPLETE, found));
            }


        } else {
            return echo(Messages.MARK_NOT_FOUND);
        }
    }

    /**
     * Delete an existing task based on its index.
     *
     * @param input The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws IncorrectIndexException If the index does not refer to a valid task.
     */
    private static String delete(String input) throws NotEnoughInputsException, IncorrectIndexException {
        String[] params = input.split(" ", 2);
        if (params.length == 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(Exceptions.NOT_ENOUGH_INPUTS,
                            "delete",
                            CorrectUsage.DELETE));
        }
        Task deleted = Tasks.delete(params[1]);
        return echo(String.format(Messages.DELETE, params[1], deleted)) + "\n" + Tasks.list();
    }



    private static String find(String input) throws NotEnoughInputsException {
        String[] params = input.split(" ", 2);
        if (params.length == 1 || params[1].isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(Exceptions.NOT_ENOUGH_INPUTS, "find", CorrectUsage.FIND));
        }
        String key = input.split(" ", 2)[1];
        if (Objects.equals(Tasks.find(key), "Not found")) {
            return echo(Messages.FIND_NOT_FOUND);
        } else {
            return echo(Messages.FIND) + "\n" + Tasks.find(key);
        }
    }

    /**
     * Prints the help message.
     */
    private static String help() {
        return Help.getHelpMessage();
    }

    /**
     * Parse the user's input and assigns them to separate helper functions depending on command
     *
     * @param isLoop Condition whether to terminate loop.
     * @param input The user's input.
     * @return Whether the loop should continue (Usually true unless "bye" command is given).
     * @throws SquidException General exception thrown by Squid.
     */
    public static Response parseInput(boolean isLoop, String input) {
        String[] messages = input.split(" ", 2);
        String command = messages[0];
        String res = "";

        try {
            switch (command) {
            case ("bye"):
                isLoop = false;
                res = bye();
                break;
            case ("echo"):
                res = echo(input, true);
                break;
            case ("list"):
                res = list();
                break;
            case ("mark"):
                res = mark(input, true);
                break;
            case ("unmark"):
                res = mark(input, false);
                break;
            case ("todo"):
                res = todo(input);
                break;
            case ("deadline"):
                res = deadline(input);
                break;
            case ("event"):
                res = event(input);
                break;
            case ("delete"):
                res = delete(input);
                break;
            case ("save"):
                Tasks.save();
                res = echo(Messages.SAVE);
                break;
            case ("find"):
                res = find(input);
                break;
            case ("help"):
                res = help();
                break;
            default:
                throw new IncorrectInputException(Exceptions.INCORRECT_INPUT);
            }
        } catch (SquidException e) {
            res = echo(e.getMessage());
        }
        res += "\n" + Messages.LINE_BREAK;
        res = Messages.LINE_BREAK + res;
        Tasks.save();
        return new Response(isLoop, res);
    }



    public static void main(String[] args) {
        new Squid();

        Scanner scanner = new Scanner(System.in);
        boolean isLoop = true;

        while (isLoop) {
            String input = scanner.nextLine().strip();
            Response response = parseInput(isLoop, input);
            isLoop = response.getIsLoop();
            Tasks.save();
        }
    }


}
