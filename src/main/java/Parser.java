import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {

    HashMap<String, Command> commands = new HashMap<>();
    ItemList itemList;

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

    public static LocalDateTime parseDTString(String s) throws DateTimeParseException {
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

    public String parse(String command) throws CustomExceptions{
        String[] arr = command.split(" ");
        try {
            return commands.get(arr[0]).execute(command, arr, itemList);
        } catch (NullPointerException e) {
            throw new CustomExceptions.unrecognizedCommandException("");
        }
    }
}
