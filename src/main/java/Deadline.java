import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        // Format: Oct 15 2019, 1800
        return String.format("[D]%s (by: %s %d %d, %d:%d)",
                super.toString(),
                time.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(),
                time.getDayOfMonth(),
                time.getYear(),
                time.getHour(),
                time.getMinute());
    }

    @Override
    public String toSavedString() {
        return String.format("D#!#%s#!#%s\n",
                super.toSavedString(),
                time.toString());
    }
}
