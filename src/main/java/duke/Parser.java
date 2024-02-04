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
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

public class Parser {

    private static DateTimeFormatterBuilder[] dtFormats = {
            new DateTimeFormatterBuilder().append(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy-HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy-HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy HHmm"),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy-hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("dd/MM/yy hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy-hh:mma"),
            new DateTimeFormatterBuilder().appendPattern("dd-MM-yy hh:mma")
    };
    private HashMap<String, Command> commands = new HashMap<>();
    private ItemList itemList;

    public Parser(ItemList itemlist) {
        this.itemList = itemlist;
        commands.put("event", new AddCommand());
        commands.put("todo", new AddCommand());
        commands.put("deadline", new AddCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("list", new ListCommand());
    }

    public static LocalDateTime parseDtString(String s) throws DateTimeParseException {
        DateTimeParseException thrown = null;
        for (DateTimeFormatterBuilder f : dtFormats) {
            try {
                LocalDateTime dt = LocalDateTime.parse(s.trim(), f.toFormatter());
                return dt;
            } catch (DateTimeParseException e) {
                thrown = e;
            }
        }
        throw thrown;
    }

    public String parse(String command) throws CustomExceptions {
        String[] arr = command.split(" ");
        try {
            return commands.get(arr[0]).execute(command, arr, itemList);
        } catch (NullPointerException e) {
            throw new CustomExceptions.UnrecognizedCommandException("");
        }
    }
}
