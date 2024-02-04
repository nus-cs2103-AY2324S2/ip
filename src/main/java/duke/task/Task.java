package duke.task;

public abstract class Task {

    protected Boolean done;
    protected final String name;
    protected Type type;
    protected String file_format;
    enum Type {
        T,D,E;

        @Override
        public String toString() {
            return name(); // Returns the name of the enum constant (e.g., "T", "D", "E")
        }
    }

    Task(String name, Type type, String file_format) {
        this.done = false;
        this.name = name;
        this.type = type;
        this.file_format = file_format;
    }

    // Mark task as done
    public void mark() {
        this.done = true;
    }

    // Unmark task
    public void unmark() {
        this.done = false;
    }
    public String getName() {
        return this.name;
    }
    public String getFileFormat() {
        return this.file_format;
    }

}
