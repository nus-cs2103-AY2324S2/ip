package leto.tasklist;

public class Todo extends Task {
    public Todo(String message) {
        super(message);
    }

    public Todo(Boolean completed, String message) { super(completed, message); }

    /**
     * Create an Todo task from csv format, Type,Completed,Task,By,From,To
     * @param entry text string containing the row in the csv
     * @return an Event task
     */
    public static Todo TodoFromCSV(String entry) {
        String[] parts = entry.split(",");
        Boolean completed = parts[1].equals("Y");
        String message = parts[2];
//        String fromString = parts[3];
//        String toString = parts[4];
        return new Todo(completed, message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the object as a row in a csv table according to format
     * Type,Completed,Task,By,From,To
     * @return String in csv format
     */
    @Override
    public String toCSVString() {
        return "T," + super.toCSVString() + ",,,";
    }
}
