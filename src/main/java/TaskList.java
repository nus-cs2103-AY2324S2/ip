import java.util.ArrayList;

public class TaskList {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static int listSize() {
        return tasks.size();
    }

    public static Task getTask(int i) {
        return tasks.get(i);
    }

    public static void addTask(Task task) {
        tasks.add(task);
        if (task instanceof Todo) {
            Ui.addTodoMessage((Todo) task);
        } else if (task instanceof Deadline) {
            Ui.addDeadlineMessage((Deadline) task);
        } else if (task instanceof Event) {
            Ui.addEventMessage((Event) task);
        }
        System.out.println("wrong task type added, user should not reach here");
    }

    public static void addTaskNoMessage(Task task) {
        tasks.add(task);
    }

    public static void listTasks() {
        Ui.listMessage();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Ui.indent() + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void markTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        Ui.markMessage(task);
    }

    public static void unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.unmark();
        Ui.unmarkMessage(task);
    }

    public static void deleteTask(int i) {
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        Ui.deleteMessage(task);
    }
}
