import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.LocalDateTime;

public class Event extends Task{
    LocalDateTime beginTime;
    LocalDateTime endTime;
    public Event(String description, LocalDateTime beginTime, LocalDateTime endTime) {
        super(description);
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %d %d, %s to: %s %d %d, %s)",
                super.toString(),
                beginTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                beginTime.getDayOfMonth(),
                beginTime.getYear(),
                LocalTime.of(beginTime.getHour(), beginTime.getMinute()),
                endTime.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                endTime.getDayOfMonth(),
                endTime.getYear(),
                LocalTime.of(endTime.getHour(), endTime.getMinute()));
    }

    @Override
    public String toSavedString() {
        return String.format("E#!#%s#!#%s#!#%s\n",
                super.toSavedString(),
                beginTime.toString(),
                endTime.toString());
    }
}
