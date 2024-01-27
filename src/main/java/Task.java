public class Task {
    private final String description;
    private boolean isDone;
    private final String startTime;
    private final String endTime;
    private final TaskType taskType;

    public Task(String description, boolean isDone, String startTime, String endTime, TaskType taskType) {
        this.description = description;
        this.isDone = isDone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }

    public static Task generateTask(String fullDescription, String type)
            throws DukeException {
        if (type.equals("todo")) {
            if (fullDescription.isEmpty()) {
                throw new DukeException("ERROR! todo descriptions cannot be empty" +
                        " nor only containing whitespaces.");
            }
            return new Todo(fullDescription, false);
        } else if (type.equals("deadline")) {
            String[] splitArr = fullDescription.split(" /by ");
            try {
                return new Deadline(splitArr[0], false, splitArr[1]);
            } catch (IndexOutOfBoundsException err) {
                throw new DukeException(
                    "ERROR! deadline descriptions cannot be empty and must have a /by" +
                    " property."
                );
            }
        } else {
            String[] splitArr = fullDescription.split("( /from )|( /to )");
            try {
                return new Event(splitArr[0], false, splitArr[1], splitArr[2]);
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
        boolean mark = Boolean.parseBoolean(lineArr[1]);
        if (lineArr[0].equals("T")) {
            return new Todo(lineArr[2], mark);
        } else if (lineArr[0].equals("D")) {
            return new Deadline(lineArr[2], mark, lineArr[3]);
        } else {
            return new Event(lineArr[2], mark, lineArr[3], lineArr[4]);
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

    public boolean getTypeEquals(TaskType taskType) {
        return this.taskType.equals(taskType);
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String addMark = this.getDone() ? "X" : " ";
        return String.format("[%s] %s", addMark, this.getDescription());
    }
}
