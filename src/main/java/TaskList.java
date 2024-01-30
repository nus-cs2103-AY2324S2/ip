import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        taskList = tasks;
    }

    public static void mark(int num) throws IOException {
        Task task = taskList.get(num);
        task.markAsDone();
        Storage.arrayToFile(taskList);
        Ui.printingMark(task.toString());
    }

    public static void unmark(int num) throws IOException {
        Task task = taskList.get(num);
        task.markAsUndone();
        Storage.arrayToFile(taskList);
        Ui.printingUnmark(task.toString());
    }

    public static void toDo(String out) throws IOException {
        Task task = new ToDos(out);
        taskList.add(task);
        Storage.arrayToFile(taskList);
        Ui.printingAdd(task.toString(), taskList.size());
    }

    public static void deadline(String description, LocalDate date) throws IOException {
        Task task = new Deadlines(description, date);
        taskList.add(task);
        Storage.arrayToFile(taskList);
        Ui.printingAdd(task.toString(), taskList.size());
    }

    public static void event(String description, String from, String to) throws IOException {
        Task task = new Events(description, from, to);
        taskList.add(task);
        Storage.arrayToFile(taskList);
        Ui.printingAdd(task.toString(), taskList.size());
    }

    public static void delete(int num) throws IOException {
        Task task = taskList.get(num);
        taskList.remove(num);
        Storage.arrayToFile(taskList);
        Ui.printingDelete(task.toString(), taskList.size());
    }

    public static void list() {
        Ui.printingList(taskList);
    }
}
