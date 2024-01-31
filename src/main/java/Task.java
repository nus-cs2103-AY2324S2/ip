import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    /** Description of task as a string */
    private String description;

    /** Boolean Flag of whether the task is done */
    private boolean isDone;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructs task with specified description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with done flag specified as a string. This method is used for storage activities.
     *
     * @param description Brief description of task.
     * @param isDone String representing boolean value.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Sets this task as done */
    public void setDone() {
        this.isDone = true;
    }

    /** Sets this task as not done */
    public void setNotDone() {
        this.isDone = false;

    }

    @Override
    public String toString() {
        String mark = this.isDone ? "[X] " : "[ ] ";
        return mark + this.description;
    }

    public static DateTimeFormatter getDateFormat() {
        return Task.DATE_FORMATTER;
    }

    /**
     * Returns a string containing information of task in a clean machine-readable format
     *
     * @return string with tokens separated by space
     */
    public abstract String getTokens();

    public static class ToDos extends Task {
        public ToDos(String name) {
            super(name);
        }

        public ToDos(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }

        /**
         * @inheritDoc
         *
         * @return description + isDone + from + to
         */
        @Override
        public String getTokens() {
            return String.join(",", "T", super.description, String.valueOf(super.isDone));
        }
    }

    public static class Events extends Task {
        private LocalDate from;
        private LocalDate to;
        public Events(String name, LocalDate from, LocalDate to) {
            super(name);
            this.from = from;
            this.to = to;
        }
        public Events(String name, boolean isDone, LocalDate from, LocalDate to) {
            super(name, isDone);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from.format(Task.getDateFormat())
                    + " to: " + this.to.format(Task.getDateFormat()) + ")";
        }

        /**
         * @inheritDoc
         *
         * @return description + isDone + from + to
         */
        @Override
        public String getTokens() {
            return String.join(",", "E",
                    super.description,
                    String.valueOf(super.isDone),
                    this.from.format(Task.getDateFormat()),
                    this.to.format(Task.getDateFormat()));
        }
    }

    public static class Deadlines extends Task {
        private LocalDate by;

        /**
         * Contructs new deadlibe object with a description and a due date.
         *
         * @param description Brief description of task.
         * @param by LocalDateTine object
         */
        public Deadlines(String description, LocalDate by) {
            super(description);
            this.by = by;
        }

        public Deadlines(String name, boolean isDone, LocalDate by) {
            super(name, isDone);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.by.format(Task.getDateFormat()) + ")";
        }

        /**
         * @inheritDoc
         *
         * @return description + isDone + from + to
         */
        @Override
        public String getTokens() {
            return String.join(",", "D", super.description,
                    String.valueOf(super.isDone), this.by.format(Task.getDateFormat()));
        }
    }
}