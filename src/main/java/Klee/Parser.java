package Klee;

import Klee.command.*;

import java.time.LocalDateTime;

/**
 * Represents a parser to parse String input from the user.
 */
public class Parser {
    /**
     * Constructor for Parser class.
     */
    public Parser() {}

    /**
     * Parse the String dateTime into a LocalDateTime instance.
     * If the syntax of dateTime cannot be understood throw KleeException.
     *
     * @param dateTime
     * @return LocalDateTime instance.
     * @throws KleeException
     */
    public static LocalDateTime parseDateTime(String dateTime) throws KleeException {
        String[] splitDateTime = dateTime.split(" ");
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        boolean hasTime = false;
        if (splitDateTime.length > 2) {
            // The input does not fit the syntax
            throw new KleeException("Time and date should be written with only 1 space between them.");
        } else if (splitDateTime.length == 2) {
            //There is a space which indicates that time is given
            hour = Integer.parseInt(splitDateTime[1].substring(0, 2));
            minute = Integer.parseInt(splitDateTime[1].substring(2, 4));
            hasTime = true;
        }

        // Test which syntax of date was used
        String[] splitDate = splitDateTime[0].split("-");
        if (splitDate.length == 3) {
            year = Integer.parseInt(splitDate[0]);
            month = Integer.parseInt(splitDate[1]);
            day = Integer.parseInt(splitDate[2]);
        } else if (splitDate.length == 1) {
            splitDate = splitDateTime[0].split("/");
            if (splitDate.length == 3) {
                year = Integer.parseInt(splitDate[2]);
                month = Integer.parseInt(splitDate[1]);
                day = Integer.parseInt(splitDate[0]);
            } else {
                throw new KleeException("Dates should only be written like 27/1/2024 or 2024-1-27");
            }
        } else {
            throw new KleeException("Dates should only be written like 27/1/2024 or 2024-1-27");
        }

        LocalDateTime returnVariable = LocalDateTime.of(year, month, day, hour, minute);
        return returnVariable;
    }

    /**
     * Given input String try to derive which command it is and return an instance of Command.
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
            String[] command = input.split(" ");
            switch (command[0]) {
            case "mark":
                if (command.length == 2) {
                    try {
                        Integer index = Integer.parseInt(command[1]) - 1;
                        if (index >= 0) {
                            return new Mark(index);
                        } else {
                            throw new KleeException("Is that a negative number? I usually start counting from number 1...");
                        }
                    } catch (Exception e) {
                        throw new KleeException("There should be an integer after mark to indicate which task to mark!");
                    }
                } else {
                    throw new KleeException("There should be an integer after mark to indicate which task to mark!");
                }
            case "unmark":
                if (command.length == 2) {
                    try {
                        Integer index = Integer.parseInt(command[1]) - 1;
                        if (index >= 0) {
                            return new Unmark(index);
                        } else {
                            throw new KleeException("Is that a negative number? I usually start counting from number 1...");
                        }
                    } catch (Exception e) {
                        throw new KleeException("There should be an integer after unmark to indicate which task to unmark!");
                    }
                } else {
                    throw new KleeException("There should be an integer after unmark to indicate which task to unmark!");
                }
            case "todo":
                try {
                    String description = input.split("todo ")[1];
                    return new ToDo(description);
                } catch (Exception e) {
                    throw new KleeException("The correct way to indicate a todo is `todo [description]`");
                }
            case "deadline":
                String[] splitDeadline = input.split("deadline ");
                try {
                    String[] deadlineProps = splitDeadline[1].split(" /by ");
                    return new Deadline(deadlineProps[0], parseDateTime(deadlineProps[1]));
                } catch (Exception e) {
                    throw new KleeException("The correct way to indicate a deadline is `deadline [description] /by [date and time]`");
                }
            case "event":
                String[] splitEvent = input.split("event ");
                try {
                    String[] eventProps = splitEvent[1].split(" /from ");
                    String description = eventProps[0];
                    eventProps = eventProps[1].split(" /to ");
                    return new Event(description, parseDateTime(eventProps[0]), parseDateTime(eventProps[1]));
                } catch (Exception e) {
                    throw new KleeException("The correct way to indicate a event is `event [description] /from [date and time] /to [date and time]`");
                }
            case "find":
                try {
                    String searchTerm = input.split("find ")[1];
                    return new Find(searchTerm);
                } catch (Exception e) {
                    throw new KleeException("The correct way to find a task is `find [Search Term]`");
                }
            case "delete":
                if (command.length == 2) {
                    try {
                        Integer index = Integer.parseInt(command[1]) - 1;
                        if (index >= 0) {
                            return new Delete(index);
                        } else {
                            throw new KleeException("Is that a negative number? I usually start counting from number 1...");
                        }
                    } catch (Exception e) {
                        throw new KleeException("There should be an integer after delete to indicate which task to delete!");
                    }
                } else {
                    throw new KleeException("There should be an integer after delete to indicate which task to delete!");
                }
            default:
                throw new KleeException("Klee doesn't understand, what are you talking about?");
            }
        }
    }
}