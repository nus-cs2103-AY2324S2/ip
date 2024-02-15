import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Task {
    private String name;
    private boolean isDone;
    private static final String[] dateFormats = { "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", "dd/MM/yyyy", "MM/dd/yyyy",
            "yyyy/MM/dd", "dd MMM yyyy", "MMM dd yyyy", "yyyy MMM dd", "dd MMM yyyy", "yyyy-MM-d", "d-MM-yyyy",
            "MM-d-yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy/MM/d", "d MMM yyyy", "MMM d yyyy", "yyyy MMM d",
            "d MMM yyyy" };
    private static final String[] timeFormats = { "HH:mm", "HH:mm", "h:mm a", "HHmm", "hh:mm a" };

    Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    Task(String name, boolean isDone) {
        this.isDone = isDone;
        this.name = name;
    }

    /**
     * Marks the task as Done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * 
     * @param dateTimeString
     * @return
     */
    public LocalDateTime convertDateTime(String dateTimeString) {
        for (String dateFormat : dateFormats) {
            for (String timeFormat : timeFormats) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat + " " + dateFormat);
                    return LocalDateTime.parse(dateTimeString, formatter);
                } catch (DateTimeParseException e) {

                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat + " " + timeFormat);
                    return LocalDateTime.parse(dateTimeString, formatter);
                } catch (DateTimeParseException e) {

                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[ ] %s", this.name);
        }
    }
}