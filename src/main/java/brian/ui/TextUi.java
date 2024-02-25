package brian.ui;

import java.util.List;
import java.util.Scanner;

import brian.task.Task;

public class TextUi {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    private StringBuilder sb = new StringBuilder();

    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    public void showFileLoadingError() {
        sb.append("Error loading file").append("\n");
    }

    public void showWelcome() {
        sb.append("Hello! I'm Brian\nWhat can I do for you?").append("\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String errorMessage) {
        sb.append(errorMessage).append("\n");
    }

    public void showTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        sb.append("\n");

    }

    public void foundTasks(List<Task> tasks) {
        sb.append("Here are the matching tasks in your list:").append("\n");
        showTasks(tasks);
    }

    public void showMarked(Task task) {
        sb.append("Nice! I've marked this task as done:").append("\n");
        sb.append(task).append("\n");
    }

    public void showUnmarked(Task task) {
        sb.append("Okay! I've marked this task as not done yet").append("\n");
        sb.append(task).append("\n");

    }

    public void showHelpCommand() {
    }

    public void showTask(Task task, int size) {
        sb.append("Got it. I've added this task:").append("\n");
        sb.append(task).append("\n");
        sb.append(String.format("Now you have %d tasks in the list.\n", size)).append("\n");
    }

    public void removeTask(Task task, int size) {
        sb.append("Noted. I've removed this task:").append("\n");
        sb.append(task).append("\n");
        sb.append(String.format("Now you have %d tasks in the list.\n", size)).append("\n");
    }

    public void showBye() {
        sb.append("Bye. Hope to see you again soon!").append("\n");
    }

    public String showResponse() {
        String result = sb.toString();
        sb = new StringBuilder();
        return result;
    }
}
