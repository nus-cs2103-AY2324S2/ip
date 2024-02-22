package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.HashMap;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;

/**
 * The Parser class helps to parse string inputs into both
 * DateTime objects and Command objects. It also handles exceptions
 * that are not CustomExceptions, and throw the equivalent CustomExceptions
 * to be caught by Duke.
 */
public class Parser {

    private static final DateTimeFormatterBuilder[] dtFormats = {
            new DateTimeFormatterBuilder().append(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy-HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy-HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy-hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy-hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("d/M/y HHmm")
    };
    private final HashMap<String, Command> commands = new HashMap<>();

    private final ItemList itemList;


    /**
     * Creates a parser object, and populates the commands hashmap
     * with recognized commands. This makes the command range more
     * easily extensible.
     *
     * @param itemlist is the current instance of the ItemList that is
     *                 being modified by the current Duke instance Elias.
     */
    public Parser(ItemList itemlist) {
        this.itemList = itemlist;
        commands.put("event", new AddCommand());
        commands.put("todo", new AddCommand());
        commands.put("deadline", new AddCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("list", new ListCommand());
        commands.put("find", new FindCommand());
        commands.put("sort", new SortCommand());
    }

    /**
     * Parses a string input into a LocalDateTime object. If the input
     * cannot be parsed according to any format in dtFormats,
     * it throws a DateTimeParse Exception.
     *
     * @param s is the input string to be parsed as a LocalDateTime object.
     * @return a LocalDateTime object.
     * @throws DateTimeParseException when the input fails to parse, based on
     *                                the above list of patterns in dtFormats.
     */
    public static LocalDateTime parseDtString(String s) throws DateTimeParseException {
        DateTimeParseException thrown = null;
        for (DateTimeFormatterBuilder f : dtFormats) {
            try {
                return LocalDateTime.parse(s.trim(), f.toFormatter());
            } catch (DateTimeParseException e) {
                thrown = e;
            }
        }
        assert thrown != null;
        throw thrown;
    }

    /**
     * Parses a string input into a LocalDateTime object. If the input
     * cannot be parsed, it throws a DateTimeParse Exception.
     *
     * @param command is the input string to be parsed as a command object.
     * @return a string that confirms that the command has been executed correctly.
     * @throws CustomExceptions.UnrecognizedCommandException when the input fails
     *                                                       to parse, based on the
     *                                                       map containing commands.
     */
    public String parse(String command) throws CustomExceptions {
        String[] arr = command.split(" ");
        try {
            return commands.get(arr[0]).execute(command, arr, itemList);
        } catch (NullPointerException e) {
            throw new CustomExceptions.UnrecognizedCommandException("");
        }
    }
}
