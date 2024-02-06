abstract public class Task {
    private final String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    boolean isDone() {
        return done;
    }

    void mark() {
        this.done = true;
    }

    void unmark() {
        this.done = false;
    }

    static Task lineToTask(String line) throws DukeException {
        String[] lineSplit = line.split(" \\| ");
        switch (lineSplit[0]) {
        case "T":
            Task newTask = new Todo(lineSplit[2]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "D":
            if (lineSplit.length < 4) {
                throw new DukeException("    Oops! The file format is wrong!\n");
            }
            newTask = new Deadline(lineSplit[2], lineSplit[3]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "E":
            if (lineSplit.length < 5) {
                throw new DukeException("    Oops! The file format is wrong!\n");
            }
            newTask = new Event(lineSplit[2], lineSplit[3], lineSplit[4]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        default:
            throw new DukeException("    Oops! The file format is wrong!\n");
        }
    }

    String taskToLine() {
        String mark = "X";
        if (done) {
            mark = "O";
        }
        return mark + " | " + name;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
