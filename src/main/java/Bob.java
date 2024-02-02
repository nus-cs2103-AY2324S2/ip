import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    private static final ArrayList<Task> TASKS = new ArrayList<>();

    // TODO: Might need to move these to a separate class
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static Task mark(int taskIndex, boolean done) {
        Task task = TASKS.get(taskIndex);
        task.setDone(done);
        return task;
    }

    public static void handleMark(int taskIndex, boolean done) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TASKS.size()) {
            throw new InvalidTaskIndexException();
        }

        Task task = mark(taskIndex, done);
        Ui.mark(task, done);

        Storage.save(TASKS, false);
    }

    public static void handleDelete(int taskIndex) throws InvalidTaskIndexException {
        if (taskIndex < 0 || taskIndex >= TASKS.size()) {
            throw new InvalidTaskIndexException();
        }

        Task task = TASKS.get(taskIndex);
        TASKS.remove(taskIndex);
        Ui.delete(task, TASKS.size());

        Storage.save(TASKS, false);
    }

    public static void handleList() {
        Ui.list(TASKS);
    }

    public static Task addTask(String taskType, String[] parameters) {
        Task task;
        switch (taskType) {
            case Parser.TODO:
                task = new Todo(parameters[0]);
                break;
            case Parser.DEADLINE:
                task = new Deadline(parameters[0], parameters[1]);
                break;
            default:
                task = new Event(parameters[0], parameters[1], parameters[2]);
        }

        TASKS.add(task);

        return task;
    }

    public static void handleAdd(String taskType, String[] parameters) {
        try {
            Task task = addTask(taskType, parameters);
            Ui.add(task, TASKS.size());
        } catch (DateTimeParseException e) {
            Ui.print(Ui.INVALID_DATE_FORMAT);
            return;
        }

        Storage.save(TASKS, true);
    }

    public static void main(String[] args) {
        Storage.load();
        Ui.print(Ui.GREET);

        boolean isNotExit = true;
        while (isNotExit) {
            isNotExit = Parser.listen();
        }
    }
}
