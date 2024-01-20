public abstract class Task {
    private String name;
    private boolean marked = false;
    public Task(String name) {
        this.name = name;
    }

    public void setDone() {
        this.marked = true;
        System.out.println("Thats sick! Great work, marked as done!\n" + this.toString());
    }

    public void setNotDone() {
        this.marked = false;
        System.out.println("Awh why uncheck me :( Its ok, it is what it is!\n" + this.toString());
    }

    @Override
    public String toString() {
        String mark = this.marked ? "[X] " : "[ ] ";
        return mark + this.name;
    }

    public static class ToDos extends Task {
        public ToDos(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
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

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        }
    }

    public static class Deadlines extends Task {
        private String by;
        public Deadlines(String name, String by) {
            super(name);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
}