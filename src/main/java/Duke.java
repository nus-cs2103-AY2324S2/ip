import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Utils {
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use dd/MM/yyyy");
        }
    }

    static String formatDate(LocalDate date) {
        return date.format(dateFormat);
    }
}

enum TodoState {
    UNDONE, DONE
}

class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    public Todo(String task, TodoState todoState) {
        super(task, todoState);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (todoState == TodoState.DONE ? "1" : "0") + " | " + task;
    }
}

class Deadline extends Task {
    LocalDate deadline;

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDate deadline, TodoState todoState) {
        super(task, todoState);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (todoState == TodoState.DONE ? "1" : "0") + " | " + task + " | " + Utils.formatDate(deadline);
    }
}

class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String task, LocalDate start, LocalDate end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    public Event(String task, LocalDate start, LocalDate end, TodoState todoState) {
        super(task, todoState);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (todoState == TodoState.DONE ? "1" : "0") + " | " + task + " | " + Utils.formatDate(start)
                + " | " + Utils.formatDate(end);
    }
}

abstract class Task {
    TodoState todoState;
    String task;

    public Task(String task) {
        this.todoState = TodoState.UNDONE;
        this.task = task;
    }

    public Task(String task, TodoState todoState) {
        this.todoState = todoState;
        this.task = task;
    }

    @Override
    public String toString() {
        return "[" + (todoState == TodoState.DONE ? "X" : " ") + "] " + task;
    }

    public abstract String toFileString();
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

class DukeConfig {
    private final String filePath;

    public DukeConfig(String filePath) {
        this.filePath = filePath;
    }

    public static DukeConfig Default() {
        return new DukeConfig("./data/duke.txt");
    }

    public String getFilePath() {
        return filePath;
    }
}

public class Duke {
    private Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Duke(DukeConfig config) throws IOException {
        ui = new Ui();
        storage = new FileStorage(config.getFilePath());
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke(DukeConfig.Default()).run();
    }
}
