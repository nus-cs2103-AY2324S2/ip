public abstract class Task {
    protected String description;
    private boolean isDone;

    protected TaskType type;

    public static String delimiter;
    public static String command;

    protected static String dataStringSplitter = " \\| ";

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

    public void setIsDone(String isDoneUpdateString, boolean isDone) {

        // obsolete toggles
        this.isDone = isDone;
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

    public String convertToDataRow() {
        return printType() + " | " + boolToInt(isDone) + " | " + description;
    }

    private static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
    public abstract String printType();
    public abstract void setUpTask(String input) throws MissingInputFieldException;

    public static Task convertDataToTask(String dataRow) {
        String[] inputArray = NextWords(Task.NextWords(dataRow.split(dataStringSplitter)));
        if (!isTaskStringArray(inputArray)) throw new RuntimeException("Data Corrupted: No Matching Task Type");
        try {
            Task temp = null;
            if (inputArray[0].equals("T")) {
                temp = createTask("todo", "todo " + inputArray[2]);
            } else if (inputArray[0].equals("E")) {
                temp = createTask("event", "event " + inputArray[2] + " /from " + inputArray[3] + " /to" + inputArray[4]);
            } else if (inputArray[0].equals("D")) {
                temp = createTask("deadline", "deadline "+ inputArray[2] + " /by " + inputArray[3]);
            } else {
                throw new RuntimeException("Data Corrupted: No Matching Task Type");
            }
            if (isTaskDataEntryDone(inputArray)) temp.isDone = true;
            return temp;
        } catch (MissingInputFieldException | ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("Data Corrupted: Missing Input Field");
        }
    }

    private static boolean isTaskStringArray(String[] inputArray) {
        return inputArray[0].equals("T") ||
                inputArray[0].equals("E") ||
                inputArray[0].equals("D");
    }

    private static boolean isTaskDataEntryDone(String[] inputArray) {
        return inputArray[1].equals("1");
    }
}
