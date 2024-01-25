public abstract class Task {
    protected String description;
    private boolean isDone;

    protected TaskType type;

    public static String delimiter;
    public static String command;

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        UNRECOGNIZED
    }
    // temporary solution
    public static String[] NextWords(String[] words) {

        String[] result = new String[words.length];
        int index = 0;

        for (String s : words) {
            if (!s.isEmpty()) {
                result[index] = s;
                index++;
            }
        }

        return result;
    }

    public static Task createTask(String type, String input) throws MissingInputFieldException {
        if (type.equals("todo")) {
            return new ToDo(input);
        } else if (type.equals("deadline")) {
            return new Deadline(input);
        } else if (type.equals("event")) {
            return new Event(input);
        }

        return null;
    }



    public Task(TaskType type) {
        this.type = type;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void toggleIsDone(String isDoneUpdateString) {

        // obsolete toggles
        if (isDoneUpdateString.equals("mark") && isDone) return;
        if (isDoneUpdateString.equals("unmark") && !isDone) return;
        isDone = !isDone;
    }

    protected char getIsDoneStatus() {
        return isDone ? 'X' : ' ';
    }

    public String updateIsDoneMessage() {
        if (isDone) return "Nice! I've marked this task as done:"
                + '\n'
                + "    "
                + toString();

        if (!isDone) return "OK, I've marked this task as not done yet:"
                + '\n'
                + "    "
                + toString();

        return null;
    }

    public String[] parseInput(String input) {
        return input.split(delimiter);
    }

    public abstract String printType();
    public abstract void setUpTask(String input) throws MissingInputFieldException;
}
