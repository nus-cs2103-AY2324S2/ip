package duke.constants;

import java.time.format.DateTimeFormatter;

public class Constant {
    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public final static DateTimeFormatter DATE_TIME_FORMATTER_FOR_PRINT = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");
    public final static String RELATIVE_PATH = "data/duke.txt";
}
