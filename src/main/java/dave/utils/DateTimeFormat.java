package dave.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeFormat {
    /** The format of the input. */
    public static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    /** The format of the output. */
    public static final DateTimeFormatter FORMATTER_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy ha");
    
}
