// TODO: divide into multiple packages
package bob;

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

    public static void handleAdd(String taskType, String[] parameters) throws DateTimeParseException {
        Task task = TaskList.add(taskType, parameters);

        TaskList.save(true);

        Ui.add(task, TaskList.getSize());
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
