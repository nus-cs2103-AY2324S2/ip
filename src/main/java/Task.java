public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    protected String getStatusSymbol() {
        return (this.isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileString() {
        return " | " + this.getStatusSymbol() + " | " + this.description;
    }

    public static Task load(String line) throws DukeException{
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];

        if (!parts[1].matches("[01]")) {
            throw new DukeException("Invalid value for isDone in the task file: " + parts[1]);
        }

        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];

        switch (taskType) {
            case "T":
                Task tT = new Todo(description);
                tT.setDone(isDone);
                return tT;
            case "D":
                Task tD = new Deadline(description, parts[3]);
                tD.setDone(isDone);
                return tD;
            case "E":
                String[] time = parts[3].split("(?<=\\d{2}:\\d{2})-(?=\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})");
                Task tE = new Event(description, time[0], time[1]);
                tE.setDone(isDone);
                return tE;
            default:
                throw new DukeException("Invalid task type " + taskType);
        }
    }
}
