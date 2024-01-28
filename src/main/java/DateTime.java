import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;

public class DateTime {
    private final String inputParseFormatStr;
    private final String outputParseFormatStr;
    private final String outputSerializeFormatStr;
    private final TemporalAccessor dateTime;

    public DateTime(String dateTimeStr) throws DateTimeParseException {
        this.inputParseFormatStr = "yyyy-MM-dd[ HH:mm]";
        this.outputParseFormatStr = "MMM dd yyyy[ hh:mma] ";
        this.outputSerializeFormatStr = "yyyy-MM-dd[ HH:mm]";
        this.dateTime = this.parseDateTimeString(dateTimeStr);
    }

    private TemporalAccessor parseDateTimeString(String dateTimeStr) throws DateTimeParseException {
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(this.inputParseFormatStr);
        TemporalAccessor temporalAccessor = formatter.parseBest(dateTimeStr, LocalDateTime::from, LocalDate::from);
        return temporalAccessor;
    }

    public String formatDateTime() {
        String output;
        if (this.dateTime instanceof LocalDateTime) {
            output = ((LocalDateTime)dateTime)
                    .format(java.time.format.DateTimeFormatter.ofPattern(this.outputParseFormatStr));
        } else {
            output = (((LocalDate)dateTime))
                    .format(java.time.format.DateTimeFormatter.ofPattern(this.outputParseFormatStr));
        }

        return output;
    }

    public String serializeDateTime() {
        String output;
        if (this.dateTime instanceof LocalDateTime) {
            output = ((LocalDateTime)dateTime)
                    .format(java.time.format.DateTimeFormatter.ofPattern(this.outputSerializeFormatStr));
        } else {
            output = (((LocalDate)dateTime))
                    .format(java.time.format.DateTimeFormatter.ofPattern(this.outputSerializeFormatStr));
        }
        return output;
    }
}
