import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class DateTimeParser {
    private final String inputParseFormatStr;
    private final String outputParseFormatStr;
    private final String outputSerializeFormatStr;

    public DateTimeParser() {
        this.inputParseFormatStr = "yyyy-MM-dd[ HH:mm]";
        this.outputParseFormatStr = "MMM dd yyyy hh:mma";
        this.outputSerializeFormatStr = "yyyy-MM-dd[ HH:mm]";
    }

    public DateTimeParser(String inputParseFormatStr, String outputParseFormatStr, String outputSerializeFormatStr) {
        this.inputParseFormatStr = inputParseFormatStr;
        this.outputParseFormatStr = outputParseFormatStr;
        this.outputSerializeFormatStr = outputSerializeFormatStr;
    }

    public LocalDateTime parseDateTimeString(String dateTimeStr) throws DateTimeParseException {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.inputParseFormatStr);
        TemporalAccessor temporalAccessor = formatter.parseBest(dateTimeStr, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            dateTime = (LocalDateTime)temporalAccessor;
        } else {
            dateTime = ((LocalDate)temporalAccessor).atStartOfDay();
        }
        return dateTime;
    }

    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(java.time.format.DateTimeFormatter.ofPattern(this.outputParseFormatStr));
    }

    public String serializeDateTime(LocalDateTime dateTime) {
        return dateTime.format(java.time.format.DateTimeFormatter.ofPattern(this.outputSerializeFormatStr));
    }
}
