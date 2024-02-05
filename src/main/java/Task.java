/**
 * Represents a user's task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static int taskCount = 0;

    /**
     * Constructor for Task class.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task class.
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X, undone task with whitespace
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     * @return Type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Increases the task count by 1.
     */
    public static void incrementTaskCount() {
        taskCount++;
    }

    /**
     * Decreases the task count by 1.
     */
    public static void decrementTaskCount() {
        taskCount--;
    }

    /**
     * Returns the task count.
     * @return Task count.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Parses a string into a Task object.
     * @return Task object.
     */
    public static Task parseTask(String taskString) throws WrongFileFormatException {
        // Checking first 7 characters to determine type and status
        if (taskString.length() < 7 || taskString.charAt(0) != '[' || taskString.charAt(2) != ']'
                || taskString.charAt(3) != '[' || taskString.charAt(5) != ']') {
            throw new WrongFileFormatException("savedTasks.txt is in the wrong format. Please delete the file and restart the program.");
        }
        String type = taskString.charAt(1) + "";
        String status = taskString.charAt(4) + "";
        boolean isDone;
        if (status.equals("X")) {
            isDone = true;
        } else if (status.equals(" ")) {
            isDone = false;
        } else {
            throw new WrongFileFormatException("savedTasks.txt is in the wrong format. Please delete the file and restart the program.");
        }
        // Parsing the rest of the string
        switch (type) {
        case "T":
            return new ToDo(taskString.substring(7), isDone);
        case "D":
            String theRestD = taskString.substring(7); // the rest of the String after type and status
            String[] splitD = theRestD.split(" \\(by: ");
            String descriptionD = splitD[0];
            try {
                String by = splitD[1].substring(0, splitD[1].length() - 1);
                return new Deadline(descriptionD, by , isDone);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new WrongFileFormatException("savedTasks.txt is in the wrong format. Please delete the file and restart the program.");
            }
        case "E":
            String theRestE = taskString.substring(7); // the rest of the String after type and status
            String[] splitE = theRestE.split(" \\(from: ");
            String descriptionE = splitE[0];
            String[] splitE2 = splitE[1].split(", to: ");
            String start = splitE2[0];
            String end = splitE2[1].substring(0, splitE2[1].length() - 1);
            return new Event(descriptionE, start, end, isDone);
        default:
            throw new WrongFileFormatException("savedTasks.txt is in the wrong format. Please delete the file and restart the program.");
        }
    }

    /**
     * Returns the string representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
