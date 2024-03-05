package ChatbotRan;

/**
 * Todos are tasks with no additional data.
 */
public class Todo extends Task {
    public Todo(String contents) {
        super(contents);
    }

    /**
     * Parses user input into a Task.
     *
     * @param line  line of user input
     * @param space index to start parsing from
     * @return Task object
     */
    public static Todo parse(String line, int space) {
        String[] texts = Util.parse(line, space);
        return new Todo(texts[0]);
    }

    @Override
    String getType() {
        return "T";
    }

    @Override
    String writeTask() {
        return String.format("T\\%b\\%s", completed, contents);
    }
}
