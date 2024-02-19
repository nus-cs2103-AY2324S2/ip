package klee;

import java.time.LocalDateTime;

import klee.command.Bye;
import klee.command.Command;
import klee.command.Deadline;
import klee.command.Delete;
import klee.command.Event;
import klee.command.Find;
import klee.command.List;
import klee.command.Mark;
import klee.command.ToDo;
import klee.command.Unmark;

/**
 * Represents a parser to parse String input from the user.
 */
public class Parser {
    /**
     * Constructor for Parser class.
     */
    public Parser() {}

    /**
     * Handle the input case 'mark'
     *
     * @param inputComponents
     * @return The Command Mark if the input is valid
     * @throws KleeException
     */
    public static Command caseMark(String[] inputComponents) throws KleeException {
        if (inputComponents.length == 2) {
            try {
                Integer index = Integer.parseInt(inputComponents[1]) - 1;
                if (index >= 0) {
                    return new Mark(index);
                } else {
                    throw new KleeException("Is that a negative number? "
                            + "I usually start counting from number 1...");
                }
            } catch (Exception e) {
                throw new KleeException("There should be an integer after mark to indicate which "
                        + "task to mark!");
            }
        } else {
            throw new KleeException("There should be an integer after mark to indicate which task to mark!");
        }
    }

    /**
     * Handle the input case 'unmark'
     *
     * @param inputComponents
     * @return The Command Unmark if the input is valid
     * @throws KleeException
     */
    public static Command caseUnmark(String[] inputComponents) throws KleeException {
        if (inputComponents.length == 2) {
            try {
                Integer index = Integer.parseInt(inputComponents[1]) - 1;
                if (index >= 0) {
                    return new Unmark(index);
                } else {
                    throw new KleeException("Is that a negative number? "
                            + "I usually start counting from number 1...");
                }
            } catch (Exception e) {
                throw new KleeException("There should be an integer after unmark to indicate which task "
                        + "to unmark!");
            }
        } else {
            throw new KleeException("There should be an integer after unmark to indicate which task to "
                    + "unmark!");
        }
    }

    /**
     * Handle the input case 'todo'
     *
     * @param input full input by user
     * @return The Command ToDo if the input is valid
     * @throws KleeException
     */
    public static Command caseToDo(String input) throws KleeException {
        try {
            String description = input.split("todo ")[1];
            return new ToDo(description);
        } catch (Exception e) {
            throw new KleeException("The correct way to indicate a todo is `todo [description]`");
        }
    }

    /**
     * Handle the input case 'deadline'
     *
     * @param input full input by user
     * @return The Command Deadline if the input is valid
     * @throws KleeException
     */
    public static Command caseDeadline(String input) throws KleeException {
        String[] deadlineComponents = input.split("deadline ");
        try {
            String[] deadlineProps = deadlineComponents[1].split(" /by ");
            return new Deadline(deadlineProps[0], parseDateTime(deadlineProps[1]));
        } catch (Exception e) {
            throw new KleeException("The correct way to indicate a deadline is `deadline [description] "
                    + "/by [date and time]`");
        }
    }

    /**
     * Handle the input case 'event'
     *
     * @param input full input by user
     * @return The Command Event if the input is valid
     * @throws KleeException
     */
    public static Command caseEvent(String input) throws KleeException {
        String[] eventComponents = input.split("event ");
        try {
            String[] eventProps = eventComponents[1].split(" /from ");
            String description = eventProps[0];
            eventProps = eventProps[1].split(" /to ");
            return new Event(description, parseDateTime(eventProps[0]), parseDateTime(eventProps[1]));
        } catch (Exception e) {
            throw new KleeException("The correct way to indicate a event is `event [description] "
                    + "/from [date and time] /to [date and time]`");
        }
    }

    /**
     * Handle the input case 'find'
     *
     * @param input full input by user
     * @return The Command Find if the input is valid
     * @throws KleeException
     */
    public static Command caseFind(String input) throws KleeException {
        try {
            String searchTerm = input.split("find ")[1];
            return new Find(searchTerm);
        } catch (Exception e) {
            throw new KleeException("The correct way to find a task is `find [Search Term]`");
        }
    }

    /**
     * Handle the input case 'delete'
     *
     * @param inputComponents the input split by blank space
     * @return The Command Delete if the input is valid
     * @throws KleeException
     */
    public static Command caseDelete(String[] inputComponents) throws KleeException {
        if (inputComponents.length == 2) {
            try {
                Integer index = Integer.parseInt(inputComponents[1]) - 1;
                if (index >= 0) {
                    return new Delete(index);
                } else {
                    throw new KleeException("Is that a negative number? I usually start counting from "
                            + "number 1...");
                }
            } catch (Exception e) {
                throw new KleeException("There should be an integer after delete to indicate which task to "
                        + "delete!");
            }
        } else {
            throw new KleeException("There should be an integer after delete to indicate which task to "
                    + "delete!");
        }
    }

    /**
     * Parse the String date into [year, month, day].
     * If the syntax of date cannot be understood throw KleeException.
     *
     * @param dateComponent
     * @return int[] representing [year, month, day].
     * @throws KleeException
     */
    public static int[] parseDate(String dateComponent) throws KleeException {
        int year = 0;
        int month = 0;
        int day = 0;
        String[] dateComponents = dateComponent.split("-");
        if (dateComponents.length == 3) {
            year = Integer.parseInt(dateComponents[0]);
            month = Integer.parseInt(dateComponents[1]);
            day = Integer.parseInt(dateComponents[2]);
        } else if (dateComponents.length == 1) {
            dateComponents = dateComponent.split("/");
            if (dateComponents.length == 3) {
                year = Integer.parseInt(dateComponents[2]);
                month = Integer.parseInt(dateComponents[1]);
                day = Integer.parseInt(dateComponents[0]);
            } else {
                throw new KleeException("Dates should only be written like 27/1/2024 or 2024-1-27");
            }
        } else {
            throw new KleeException("Dates should only be written like 27/1/2024 or 2024-1-27");
        }
        int[] returnVariable = new int[3];
        returnVariable[0] = year;
        returnVariable[1] = month;
        returnVariable[2] = day;
        return returnVariable;
    }

    /**
     * Parse the String dateTime into a LocalDateTime instance.
     * If the syntax of dateTime cannot be understood throw KleeException.
     *
     * @param dateTime
     * @return LocalDateTime instance.
     * @throws KleeException
     */
    public static LocalDateTime parseDateTime(String dateTime) throws KleeException {
        String[] dateTimeComponents = dateTime.split(" ");
        int hour = 0;
        int minute = 0;
        boolean hasTime = false;
        if (dateTimeComponents.length > 2) {
            // The input does not fit the syntax
            throw new KleeException("Time and date should be written with only 1 space between them.");
        } else if (dateTimeComponents.length == 2) {
            //There is a space which indicates that time is given
            hour = Integer.parseInt(dateTimeComponents[1].substring(0, 2));
            minute = Integer.parseInt(dateTimeComponents[1].substring(2, 4));
            hasTime = true;
        }

        int[] dateComponents = parseDate(dateTimeComponents[0]);

        LocalDateTime returnVariable = LocalDateTime.of(dateComponents[0], dateComponents[1], dateComponents[2],
                hour, minute);
        return returnVariable;
    }

    /**
     * Return an instance of Command given input String.
     * If input is in the wrong syntax throw KleeException.
     *
     * @param input
     * @return Command instance
     * @throws KleeException
     */
    public Command parseInput(String input) throws KleeException {
        switch (input) {
        case "bye":
            return new Bye();
        case "list":
            return new List();
        default:
            String[] inputComponents = input.split(" ");
            switch (inputComponents[0]) {
            case "mark":
                return caseMark(inputComponents);
            case "unmark":
                return caseUnmark(inputComponents);
            case "todo":
                return caseToDo(input);
            case "deadline":
                return caseDeadline(input);
            case "event":
                return caseEvent(input);
            case "find":
                return caseFind(input);
            case "delete":
                return caseDelete(inputComponents);
            default:
                throw new KleeException("Klee doesn't understand, what are you talking about?");
            }
        }
    }
}
