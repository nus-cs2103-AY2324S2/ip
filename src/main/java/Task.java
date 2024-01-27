public class Task {
    private final String description;
    private boolean done;
    private final String startTime;
    private final String endTime;
    private final Type type;

    public Task(String description, String startTime, String endTime, Type type) {
        this.description = description;
        this.done = false;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public static Task generateTask(String fullDescription, String type)
        throws DukeException {
        if (type.equals("todo")) {
            if (fullDescription.isEmpty()) {
                throw new DukeException("ERROR! todo descriptions cannot be empty" +
                        " nor only containing whitespaces.");
            }
            return new Todo(fullDescription);
        } else if (type.equals("deadline")) {
            String[] splitArr = fullDescription.split(" /by ");
            try {
                return new Deadline(splitArr[0], splitArr[1]);
            } catch (IndexOutOfBoundsException err) {
                throw new DukeException(
                    "ERROR! deadline descriptions cannot be empty and must have a /by" +
                    " property."
                );
            }
        } else {
            String[] splitArr = fullDescription.split("( /from )|( /to )");
            try {
                return new Event(splitArr[0], splitArr[1], splitArr[2]);
            } catch (IndexOutOfBoundsException err) {
                throw new DukeException(
                    "ERROR! event descriptions cannot be empty, and must have" +
                    " /from and /to properties."
                );
            }
        }
    }

    public static Task generateTaskFromFile(String line) {
        String[] lineArr = line.split("\\|");
        if (lineArr[0].equals("T")) {
            return new Todo(lineArr[1]);
        } else if (lineArr[0].equals("D")) {
            return new Deadline(lineArr[1], lineArr[2]);
        } else {
            return new Event(lineArr[1], lineArr[2], lineArr[3]);
        }
    }

    public String getDescription() {
        return this.description;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Type getType() {
        return this.type;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String addMark = this.getDone() ? "X" : " ";
        return String.format("[%s] %s", addMark, this.getDescription());
    }
}
