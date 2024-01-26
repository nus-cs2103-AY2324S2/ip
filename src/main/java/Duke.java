import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import CONSTANTS.MESSAGES;
import CONSTANTS.EXCEPTIONS;
import CONSTANTS.REGEX;
import CONSTANTS.CORRECT_USAGE;

import Exceptions.*;

import Tasks.Todo;
import Tasks.Event;
import Tasks.Date;
import Tasks.Deadline;
import Tasks.Task;
import Tasks.Tasks;


public class Duke {

    private static void Squid() {
        new Tasks();
    }

    private static void list() throws NotEnoughInputsException {
        echo(MESSAGES.LIST);
        Tasks.list();
    }

    private static void hello() throws NotEnoughInputsException {
        try {
            Tasks.read();
        } catch (ParseFailException | DuplicateTaskNameException | SquidDateException e) {
            echo(e.toString());
        }
        System.out.println(MESSAGES.LINE_BREAK);
        echo(CONSTANTS.MESSAGES.HELLO);
        System.out.println(MESSAGES.LINE_BREAK);
    }

    private static void bye() throws NotEnoughInputsException {
        String message = MESSAGES.BYE;
        echo(message);
        Tasks.save();
    }

    private static void echo(String message, boolean isFromUser) throws NotEnoughInputsException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "echo", CORRECT_USAGE.ECHO));
        }
        echo(params[1]);
    }

    private static void echo(String message) throws NotEnoughInputsException {
        if (message.isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "echo", CORRECT_USAGE.ECHO));
        }
        System.out.println(MESSAGES.ECHO + message);
    }

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

    private static void deadline(String message) throws NotEnoughInputsException, DuplicateTaskNameException, SquidDateException {
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
        Date date = new Date(arguments[1]);
        Task t = new Deadline(task, date);
        Tasks.add(t);
        echo(String.format(MESSAGES.DEADLINE, t));
    }

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
        Date from = new Date(dates[0]);
        Date to = new Date(dates[1]);
        Task t = new Event(params[0], from, to);
        Tasks.add(t);
        echo(String.format(MESSAGES.EVENT, t));
    }


    private static void mark(String input, boolean isCompleted) throws NotEnoughInputsException {
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
            if (Tasks.get(i).task.equals(task)) {
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
