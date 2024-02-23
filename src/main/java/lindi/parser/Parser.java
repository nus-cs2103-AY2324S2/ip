package lindi.parser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.IntStream;

import lindi.commands.Command;
import lindi.commands.CreateDeadlineCommand;
import lindi.commands.CreateEventCommand;
import lindi.commands.CreateToDoCommand;
import lindi.commands.DeleteCommand;
import lindi.commands.ExitCommand;
import lindi.commands.FindCommand;
import lindi.commands.InvalidCommand;
import lindi.commands.ListCommand;
import lindi.commands.MarkCommand;
import lindi.commands.MassOpsCommand;
import lindi.commands.UnmarkCommand;



/**
 * Deals with making sense (parsing) of the user command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command
     *
     * @param userInput the user input command string
     * @return the Command corresponding to the input string.
     */
    public static Command parse(String userInput) {
        // if command called, will be index 0, rest of string (args) in index 1
        String[] userInputTokens = userInput.split(" ", 2);
        String commandWord = userInputTokens[0];
        String args = userInputTokens.length == 2 ? userInputTokens[1] : null;

        switch (commandWord) {
        case ListCommand.COMMAND_LIST:
            return new ListCommand();

        case FindCommand.COMMAND_FIND:
            return prepareFind(args);

        case UnmarkCommand.COMMAND_UNMARK:
            return prepareUnmark(args);

        case MarkCommand.COMMAND_MARK:
            return prepareMark(args);

        case DeleteCommand.COMMAND_DELETE:
            return prepareDelete(args);

        case CreateToDoCommand.COMMAND_TODO:
            return new CreateToDoCommand(args);

        case CreateDeadlineCommand.COMMAND_DEADLINE:
            return new CreateDeadlineCommand(args);

        case CreateEventCommand.COMMAND_EVENT:
            return new CreateEventCommand(args);

        case ExitCommand.COMMAND_EXIT:
            return new ExitCommand();

        default:
            return new InvalidCommand("Uhh, English please? Haha, just kidding...\n"
                    + "but for reals I didn't really understand that :(");
        }
    }

    /**
     * Returns the index from the args
     *
     * @param args arguments from user input
     * @return index from args
     * @throws NumberFormatException if args is not a number
     */
    private static int getIndexFromArgs(String args) throws NumberFormatException {
        return Integer.parseInt(args);
    }

    /**
     * Returns an FindCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return FindCommand if args are valid. <p>InvalidCommand if args is null
     */
    private static Command prepareFind(String args) {
        if (args == null) {
            return new InvalidCommand("Please give me something to search for :)");
        }
        return new FindCommand(args);
    }
    /**
     * Returns an UnmarkCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return UnmarkCommand if args are valid. <p>InvalidCommand if args is null or non-integer
     */
    private static Command prepareUnmark(String args) {
        return prepareModify(UnmarkCommand::new, args);
    }

    /**
     * Returns an MarkCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return MarkCommand if args are valid. <p>InvalidCommand if args is null or non-integer
     */
    private static Command prepareMark(String args) {
        return prepareModify(MarkCommand::new, args);
    }

    /**
     * Returns an DeleteCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return DeleteCommand if args are valid. <p>InvalidCommand if args is null or non-integer
     */
    private static Command prepareDelete(String args) {
        return prepareModify(DeleteCommand::new, args);
    }

    /**
     * Returns the Command specified by the commandConstructor if args are valid, else InvalidCommand.
     * Returns a MassOpsCommand if args contains multiple indices or ranges, and command supports mass operations.
     *
     * @param commandConstructor constructor for the command to be created
     * @param args arguments from user input
     * @return Command if args are valid. <p>InvalidCommand if args is invalid
     */
    private static Command prepareModify(Function<Integer, Command> commandConstructor, String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index "
                    + "of the task you want to modify :)");
        }
        if (isMassOps(args)) {
            return prepareMassOps(commandConstructor, args);
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return commandConstructor.apply(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n"
                    + "there shouldn't be any characters!! :(");
        }
    }

    private static boolean isMassOps(String args) {
        return args.contains(",") || args.contains("-");
    }

    /**
     * Returns a MassOpsCommand if args are valid, else InvalidCommand
     *
     * @param commandConstructor constructor for the command to be created
     * @param args arguments from user input
     * @return MassOpsCommand if args are valid. <p>InvalidCommand if args is invalid
     */
    private static Command prepareMassOps(Function<Integer, Command> commandConstructor, String args) {
        try {
            IntStream indices = getIndicesFromArgs(args);

            HashSet<Integer> indexSet = new HashSet<>();
            for (int index : indices.toArray()) {
                if (!indexSet.add(index)) {
                    return new InvalidCommand("You have duplicate indexes in your input :(");
                }
            }

            return populateMassOpsCommand(commandConstructor, indexSet);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n"
                    + "there shouldn't be any characters!! :(");
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("Invalid mass operation" + e.getMessage());
        }
    }

    /**
     * Returns a MassOpsCommand with the commands created from the indexes
     *
     * @param commandConstructor constructor for the command to be created
     * @param indexSet set of indexes to be used to create the Commands in MassOpsCommand
     * @return MassOpsCommand with the commands created from the indexes
     */
    private static MassOpsCommand populateMassOpsCommand(
            Function<Integer, Command> commandConstructor, HashSet<Integer> indexSet) {
        MassOpsCommand massOpsCommand = new MassOpsCommand();
        indexSet.stream().sorted((a, b) -> b - a)
                .forEach(index -> massOpsCommand
                        .addCommand(commandConstructor.apply(index)));
        return massOpsCommand;
    }

    /**
     * @param args string of indices or ranges
     * @return IntStream of indices
     * @throws IllegalArgumentException if range indices are invalid
     * @throws NumberFormatException if indices are not numbers
     */
    private static IntStream getIndicesFromArgs(String args) throws IllegalArgumentException, NumberFormatException {
        return Arrays.stream(args.split(","))
                .map(String::trim)
                .flatMapToInt(Parser::flattenRangeToIndices);
    }

    /**
     * @param indexOrRange index or range string
     * @return IntStream of indices
     * @throws IllegalArgumentException if range indices are invalid
     * @throws NumberFormatException if indices are not numbers
     */
    private static IntStream flattenRangeToIndices(String indexOrRange)
            throws IllegalArgumentException, NumberFormatException {
        if (indexOrRange.contains("-")) {
            return getIndicesFromRange(indexOrRange);
        } else {
            return IntStream.of(Integer.parseInt(indexOrRange));
        }
    }

    /**
     * @param range string of range indices
     * @return IntStream of indices
     * @throws IllegalArgumentException if range indices are invalid
     * @throws NumberFormatException if indices are not numbers
     */
    private static IntStream getIndicesFromRange(String range) throws IllegalArgumentException, NumberFormatException {
        String[] endPoints = range.split("-");
        int start = Integer.parseInt(endPoints[0]);
        int end = Integer.parseInt(endPoints[1]);
        if (start > end) {
            throw new IllegalArgumentException("Start index cannot be greater than end index");
        }
        return IntStream.range(start, end + 1);
    }
}
