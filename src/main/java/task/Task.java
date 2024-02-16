package task;

/**
 * Represents a task that needs to be done.
 */
public class Task {
    private String name;
    private boolean isDone;

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

<<<<<<< HEAD
    public String getName() {
        return this.name;
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

=======
>>>>>>> branch-A-JavaDoc
    @Override
    public String toString() {
        if (this.isDone) {
            return String.format("[X] %s", this.name);
        } else {
            return String.format("[ ] %s", this.name);
        }
    }
}