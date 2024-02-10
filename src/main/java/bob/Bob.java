// TODO: divide into multiple packages
package bob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Bob {
    public static void handleMark(int taskIndex, boolean isDone) throws InvalidTaskIndexException {
        Task task = TaskList.mark(taskIndex, isDone);

        TaskList.save(false);
        Ui.mark(task, isDone);
    }

    public static void handleDelete(int taskIndex) throws InvalidTaskIndexException {
        Task task = TaskList.delete(taskIndex);
        Ui.delete(task, TaskList.getSize());
    }

    public static void handleList() {
        TaskList.list();
    }

    public static void handleListOnDate(LocalDate date) {
        TaskList.listOnDate(date);
    }

    private static void saveAndPrintAdded(Task task) {
        TaskList.save(true);
        Ui.add(task, TaskList.getSize());
    }

    public static void handleAddTodo(String description) throws DateTimeParseException {
        Task task = TaskList.addTodo(description);
        saveAndPrintAdded(task);
    }

    public static void handleAddDeadline(String description, LocalDateTime by) throws DateTimeParseException {
        Task task = TaskList.addDeadline(description, by);
        saveAndPrintAdded(task);
    }

    public static void handleAddEvent(
            String description, LocalDateTime from, LocalDateTime to) throws DateTimeParseException {
        Task task = TaskList.addEvent(description, from, to);
        saveAndPrintAdded(task);
    }

    public static void main(String[] args) {
        Storage.load();
        Ui.print(Ui.GREET);

        boolean isNotExit = true;
        while (isNotExit) {
            isNotExit = Parser.parse();
        }
    }
}
