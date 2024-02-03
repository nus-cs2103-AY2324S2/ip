import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class Parser {

    private static DateTimeFormatterBuilder[] formats = {
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
        for (DateTimeFormatterBuilder f : formats) {
            try {
                LocalDateTime dt = LocalDateTime.parse(s.trim(), f.toFormatter());
                return dt;
            } catch (DateTimeParseException e) {
                thrown = e;
            }
        }
        throw thrown;
    }
}
