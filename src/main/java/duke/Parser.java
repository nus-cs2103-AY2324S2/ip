package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ToggleMarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses user input and returns corresponding Command objects for execution.
 */
public class Parser {
    /**
     * Enumerates the different types of instructions that can be parsed.
     */
    public enum Instructions {
        BYE ("^bye", "^exit", "^quit"),
        LIST ("^list", "^l", "^ls"),
        MARK ("^mark \\d+", "^m \\d+", "^done \\d+"),
        UNMARK ("^unmark \\d+", "^u \\d+", "^um \\d+", "^undone \\d+"),
        DELETE ("^delete \\d+", "^del \\d+", "^rm \\d+"),
        TODO ("^todo .+", "^td .+"),
        DEADLINE ("^deadline .+", "^dl .+"),
        EVENT ("^event .+", "^ev .+"),
        FIND ("^find \\S+$", "^f \\S+$", "^search \\S+$"),
        HELP ("^help", "^h", "^help \\S+$", "^h \\S+$");

        /** Array of regex patterns for each instruction. */
        private final String[] patterns;

        /**
         * Constructs a new Instructions enum value with the specified regex patterns.
         *
         * @param patterns a String array representing the regex patterns for the instruction
         */
        Instructions(String... patterns) {
            this.patterns = patterns;
        }

        /**
         * Returns the array of regex patterns for the instruction.
         *
         * @return a String array containing the regex patterns for the instruction
         */
        public String[] getPatterns() {
            return patterns;
        }
    }

    /**
     * Enumerates the different types of date specifiers that can be parsed.
     */
    public enum DateSpecifiers {
        BY ("/by", "/at", "/b", "/a"),
        FROM ("/from", "/start", "/f", "/s"),
        TO ("/to", "/end ", "/t ", "/e");

        /** Array of regex patterns for each specifier. */
        private final String[] patterns;

        /**
         * Constructs a new DateSpecifiers enum value with the specified regex patterns.
         *
         * @param patterns a String array representing the regex patterns for the specifier
         */
        DateSpecifiers(String... patterns) {
            this.patterns = patterns;
        }
    }

    /**
     * Enumerates the different types of date formats that can be parsed.
     */
    public enum DateFormats {
        FORMAT1 ("dd/MM/yyyy HHmm"),
        FORMAT2 ("dd-MM-yyyy HHmm"),
        FORMAT3 ("ddMMyyyy HHmm");

        /** String representing the date format. */
        private final String format;

        /**
         * Constructs a new DateFormats enum value with the specified date format.
         *
         * @param format a String representing the date format
         */
        DateFormats(String format) {
            this.format = format;
        }
    }

    /**
     * Constructs a new Parser object.
     * This constructor does not require any parameters.
     */
    public Parser() {
        //do nothing
    }

    /**
     * Helper function that checks if a string matches a list of regex patterns.
     *
     * @param input a String taken from the command line user input
     * @param instruction an Instructions enum value representing the type of instruction to be checked
     * @return a boolean value signifying if the input matches the regex pattern
     */
    private static boolean matchesPattern(String input, Instructions instruction) {
        for (String pattern : instruction.patterns) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(input);
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper function that splits a string based on a DateSpecifier.
     *
     * @param input a String taken from the command line user input
     * @param specifier a DateSpecifiers enum value representing the type of specifier to be used
     * @return a String array containing the split input
     */
    private static String[] specifierSplitter(String input, DateSpecifiers specifier) {
        for (String spec : specifier.patterns) {
            String regexSpec = ".+ " + spec + " .+";
            Pattern p = Pattern.compile(regexSpec);
            Matcher m = p.matcher(input);
            if (m.matches()) {
                return input.split(spec, 2);
            }
        }
        throw new IllegalArgumentException("Blunder! "
                + "Declare yer task in the correct format, ye scurvy dog!");
    }

    /**
     *  Handles user input and returns an appropriate Command object
     *  to be executed in the main program.
     *
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     * @throws IllegalArgumentException if user input format is unrecognised
     */
    public static Command handleInput(String input) throws IllegalArgumentException {
        try {
            if (matchesPattern(input, Instructions.BYE)) {
                return new ExitCommand();
            } else if (matchesPattern(input, Instructions.LIST)) {
                return new ListCommand();
            } else if (matchesPattern(input, Instructions.MARK)) {
                return handleIndexedInstruction(input, Instructions.MARK);
            } else if (matchesPattern(input, Instructions.UNMARK)) {
                return handleIndexedInstruction(input, Instructions.UNMARK);
            } else if (matchesPattern(input, Instructions.DELETE)) {
                return handleIndexedInstruction(input, Instructions.DELETE);
            } else if (matchesPattern(input, Instructions.TODO)) {
                return handleTodoInstruction(input);
            } else if (matchesPattern(input, Instructions.DEADLINE)) {
                return handleDeadlineInstruction(input);
            } else if (matchesPattern(input, Instructions.EVENT)) {
                return handleEventInstruction(input);
            } else if (matchesPattern(input, Instructions.FIND)) {
                return handleFindInstruction(input);
            } else if (matchesPattern(input, Instructions.HELP)) {
                return handleHelpInstruction(input);
            } else {
                throw new IllegalArgumentException("Arrr, me apologies! I cannot fathom that.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Blunder! "
                    + "I be searchin' the seas but couldn't spy the task ye named, me heartie!");
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Blunder! "
                    + "Declare yer dates as dd/MM/yyyy HHmm"
                    + " or dd-MM-yyyy HHmm, ye scurvy dog!");
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * Helper function to handle mark, unmark and delete commands that require an index to be specified.
     * @param input a String taken from the user's command line input
     * @param instruction an Instructions enum value representing the type of instruction to be executed
     * @return a Command object corresponding to the user's input
     */
    private static Command handleIndexedInstruction(String input, Instructions instruction)
            throws IllegalArgumentException {
        String[] wordArray = input.split(" ", 0);
        Integer index = Integer.parseInt(wordArray[1]);
        switch (instruction) {
        case MARK:
            return new ToggleMarkCommand(index, true);
        case UNMARK:
            return new ToggleMarkCommand(index, false);
        case DELETE:
            return new DeleteCommand(index);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Helper function to handle todo commands.
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     */
    private static Command handleTodoInstruction(String input) {
        String[] wordArray = input.split(" ", 2);
        String tempDescription = wordArray[1].trim();
        Todo todo = new Todo(tempDescription);
        return new AddCommand(todo);
    }

    /**
     * Helper function to handle deadline commands.
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     */
    private static Command handleDeadlineInstruction(String input)
            throws IllegalArgumentException, DateTimeParseException {
        String[] tempArray = input.split(" ", 2);
        String tempString = tempArray[1];
        try {
            String[] deadlineData = specifierSplitter(tempString, DateSpecifiers.BY);
            String description = deadlineData[0].trim();
            LocalDateTime by = parseDate(deadlineData[1].trim());
            Deadline deadline = new Deadline(description, by);
            return new AddCommand(deadline);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Helper function to handle event commands.
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     */
    private static Command handleEventInstruction(String input) {
        String[] tempArray = input.split(" ", 2);
        String tempString = tempArray[1];
        try {
            String[] eventData = specifierSplitter(tempString, DateSpecifiers.FROM);
            String description = eventData[0].trim();
            tempString = eventData[1];
            eventData = specifierSplitter(tempString, DateSpecifiers.TO);
            LocalDateTime from = parseDate(eventData[0].trim());
            LocalDateTime to= parseDate(eventData[1].trim());
            Event event = new Event(description, from, to);
            return new AddCommand(event);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Helper function to handle find commands.
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     */
    private static Command handleFindInstruction(String input) {
        String[] wordArray = input.split(" ", 0);
        String keyword = wordArray[1];
        return new FindCommand(keyword);
    }

    /**
     * Helper function to handle help commands.
     * @param input a String taken from the user's command line input
     * @return a Command object corresponding to the user's input
     */
    private static Command handleHelpInstruction(String input) {
        String[] wordArray = input.split(" ", 0);
        if (wordArray.length > 1) {
            String command = wordArray[1];
            return new HelpCommand(command);
        } else {
            return new HelpCommand();
        }
    }

    /**
     * Helper function to convert a String input into a LocalDateTime object.
     *
     * @param input a String representing a date in the format dd/MM/yyyy HHmm
     * @return a corresponding LocalDateTime object
     * @throws IllegalArgumentException if date String not in the correct format
     */
    public static LocalDateTime parseDate(String input) {
        for (DateFormats format : DateFormats.values()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format.format);
                LocalDateTime time = LocalDateTime.parse(input, formatter);
                return time;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Blunder! "
                + "The date format ye provided be as tangled as a ship's riggin'.");
    }
}
