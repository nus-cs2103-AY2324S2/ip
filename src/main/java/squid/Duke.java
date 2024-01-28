package squid;

import java.util.Scanner;

import squid.constants.MESSAGES;
import squid.constants.EXCEPTIONS;
import squid.constants.REGEX;
import squid.constants.CORRECT_USAGE;

import squid.exceptions.*;

import squid.tasks.Todo;
import squid.tasks.Event;
import squid.tasks.DateTime;
import squid.tasks.Deadline;
import squid.tasks.Task;
import squid.tasks.Tasks;


public class Duke {


    private static void Squid() {
        new Tasks();
    }

    /**
     * List all existing tasks.
     *
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws IncorrectIndexException Should never happen.
     */
    private static void list() throws NotEnoughInputsException, IncorrectIndexException {
        echo(MESSAGES.LIST);
        Tasks.list();
    }

    /**
     * Initializes Squid.
     */
    private static void hello() {
        try {
            Tasks.read();
        } catch (ParseFailException | DuplicateTaskNameException | SquidDateException e) {
            echo(e.toString());
        }
        System.out.println(MESSAGES.LINE_BREAK);
        echo(squid.constants.MESSAGES.HELLO);
        System.out.println(MESSAGES.LINE_BREAK);
    }

    /**
     * Terminates the program.
     *
     * @throws NotEnoughInputsException Should never happen, unless constant MESSAGES.BYE is blank.
     */
    private static void bye() throws NotEnoughInputsException {
        echo(MESSAGES.BYE);
        Tasks.save();
    }

    /**
     * Overloaded method to include this as a valid command.
     *
     * @param message The raw input from the user, or message to be printed.
     * @param isFromUser Whether to further process message.
     * @throws NotEnoughInputsException If there are not enough parameters.
     */
    private static void echo(String message, boolean isFromUser) throws NotEnoughInputsException {
        if (!isFromUser) {
            echo(message);
            return;
        }
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "echo", CORRECT_USAGE.ECHO));
        }
        echo(params[1]);
    }

    /**
     * Prints a message with a custom header.
     *
     * @param message The message to be printed.
     */
    private static void echo(String message) {
        System.out.println(MESSAGES.ECHO + message);
    }

    /**
     * Handles the creation of a todo task.
     *
     * @param message The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws DuplicateTaskNameException If there is an existing task with the same name.
     */
    private static void todo(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "todo", CORRECT_USAGE.TODO));

        }

        Task t = new Todo(params[1]);
        Tasks.add(t);
        echo(String.format(MESSAGES.TODO, t));
    }

    /**
     * Handles the creation of a deadline task.
     *
     * @param message The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws DuplicateTaskNameException If there is an existing task with the same name.
     * @throws SquidDateException If the date given is unable to be parsed.
     */
    private static void deadline(String message) throws
            NotEnoughInputsException,
            DuplicateTaskNameException,
            SquidDateException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            "deadline",
                            CORRECT_USAGE.DEADLINE));
        }
        String[] arguments = params[1].split(REGEX.DEADLINE);

        if (arguments.length == 1) {
            throw new NotEnoughDatesException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_DATES,
                            1,
                            "deadline",
                            CORRECT_USAGE.DEADLINE));
        }
        String task = arguments[0];
        DateTime dateTime = new DateTime(arguments[1]);
        Task t = new Deadline(task, dateTime);
        Tasks.add(t);
        echo(String.format(MESSAGES.DEADLINE, t));
    }

    /**
     * Handles the creation of an event task.
     *
     * @param message The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws DuplicateTaskNameException If there is an existing task with the same name.
     * @throws SquidDateException If the date given is unable to be parsed.
     */
    private static void event(String message) throws
            NotEnoughInputsException,
            DuplicateTaskNameException,
            SquidDateException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            "event",
                            CORRECT_USAGE.EVENT));
        }
        params = params[1].split(REGEX.EVENT_FROM);
        if (params.length != 2 || params[1].split(REGEX.EVENT_TO).length != 2) {
            throw new NotEnoughDatesException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_DATES,
                            2,
                            "event",
                            CORRECT_USAGE.EVENT));
        }
        String[] dates = params[1].split(REGEX.EVENT_TO);
        DateTime from = new DateTime(dates[0]);
        DateTime to = new DateTime(dates[1]);
        Task t = new Event(params[0], from, to);
        Tasks.add(t);
        echo(String.format(MESSAGES.EVENT, t));
    }


    /**
     * Mark a task as either complete or incomplete.
     *
     * @param input The user's raw input.
     * @param isCompleted Whether to mark it completed or incomplete.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws IncorrectIndexException If the index does not refer to a valid task.
     */
    private static void mark(String input, boolean isCompleted) throws
            NotEnoughInputsException,
            IncorrectIndexException {
        String[] params = input.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            isCompleted ? "mark" : "unmark",
                            CORRECT_USAGE.MARK(isCompleted)));
        }
        String task = params[1];
        // Find the task entry.
        Task found = null;
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).taskName.equals(task)) {
                found = Tasks.get(i);
            }
        }
        if (found != null) {
            if (found.isCompleted() == isCompleted) {
                echo(String.format(MESSAGES.MARK_REPEAT,isCompleted ? "" : "un"));
            } else {
                found.setCompleted(isCompleted);
                echo(String.format(isCompleted
                        ? MESSAGES.MARK_COMPLETE
                        : MESSAGES.MARK_INCOMPLETE, found));
            }


        } else {
            echo(MESSAGES.MARK_NOT_FOUND);
        }
    }

    /**
     * Delete an existing task based on its index.
     *
     * @param input The user's raw input.
     * @throws NotEnoughInputsException If there are not enough parameters.
     * @throws IncorrectIndexException If the index does not refer to a valid task.
     */
    private static void delete(String input) throws NotEnoughInputsException, IncorrectIndexException {
        String[] params = input.split(" ", 2);
        if (params.length == 1) {
            throw new NotEnoughInputsException(
                    String.format(EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            "delete",
                            CORRECT_USAGE.DELETE));
        }
        Task deleted = Tasks.delete(params[1]);
        echo(String.format(MESSAGES.DELETE, params[1], deleted));
        Tasks.list();
    }


    /**
     * Parse the user's input and assigns them to separate helper functions depending on command
     *
     * @param loop Condition whether to terminate loop.
     * @param input The user's input.
     * @return Whether the loop should continue (Usually true unless "bye" command is given).
     * @throws DukeException General exception thrown by Squid.
     */
    private static boolean parseInput(boolean loop, String input) throws DukeException {
        System.out.println(MESSAGES.LINE_BREAK);
        String[] messages = input.split(" ", 2);
        String command = messages[0];
        String params = "";
        if (messages.length > 1) {
            params = messages[1];
        }

        try {
            switch (command) {
            case ("bye"):
                loop = false;
                bye();
                break;
            case ("echo"):
                echo(input, true);
                break;
            case ("list"):
                list();
                break;
            case ("mark"):
                mark(input, true);
                break;
            case ("unmark"):
                mark(input, false);
                break;
            case ("todo"):
                todo(input);
                break;
            case ("deadline"):
                deadline(input);
                break;
            case ("event"):
                event(input);
                break;
            case ("delete"):
                delete(input);
                break;
            case ("save"):
                Tasks.save();
                echo(MESSAGES.SAVE);
                break;
            default:
                throw new IncorrectInputException(EXCEPTIONS.INCORRECT_INPUT);
            }
        } catch (DukeException e) {
            echo(e.getMessage());
        }
        return loop;
    }

    public static void main(String[] args) throws DukeException {
        Squid();
        hello();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            String input = scanner.nextLine().strip();
            loop = parseInput(loop, input);
            System.out.println(MESSAGES.LINE_BREAK);
            Tasks.save();
        }

    }


}
