import static java.lang.Boolean.parseBoolean;

public abstract class Task {

    /** Description of task as a string */
    private String description;

    /** Boolean Flag of whether the task is done */
    private boolean isDone;

    /** Constructs task with specified description
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with done flag specified as a string. This method is used for storage activities.
     *
     * @param description string
     * @param isDone boolean value
     */
    public Task(String description, String isDone) {
        this.description = description;
        this.isDone = parseBoolean(isDone);
    }

    /** Sets this task as done */
    public void setDone() {
        this.isDone = true;
        System.out.println("Thats sick! Great work, marked as done!\n" + this.toString());
    }

    /** Sets this task as not done */
    public void setNotDone() {
        this.isDone = false;
        System.out.println("Awh why uncheck me :( Its ok, it is what it is!\n" + this.toString());
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "[X] " : "[ ] ";
        return mark + this.description;
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

        public ToDos(String description, String isDone) {
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
            return String.join(" ", "T", super.description, String.valueOf(super.isDone));
        }
    }

    public static class Events extends Task {
        private String from;
        private String to;
        public Events(String name, String from, String to) {
            super(name);
            this.from = from;
            this.to = to;
        }
        public Events(String name, String isDone, String from, String to) {
            super(name, isDone);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        }

        /**
         * @inheritDoc
         *
         * @return description + isDone + from + to
         */
        @Override
        public String getTokens() {
            return String.join(" ", "E", super.description,
                    String.valueOf(super.isDone), this.from, this.to);
        }
    }

    public static class Deadlines extends Task {
        private String by;
        public Deadlines(String name, String by) {
            super(name);
            this.by = by;
        }

        public Deadlines(String name, String isDone, String by) {
            super(name, isDone);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }

        /**
         * @inheritDoc
         *
         * @return description + isDone + from + to
         */
        @Override
        public String getTokens() {
            return String.join(" ", "D", super.description, String.valueOf(super.isDone), this.by);
        }
    }
}