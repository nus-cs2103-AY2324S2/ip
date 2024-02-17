package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class TextUi {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    public void showFileLoadingError() {
        System.out.println("Error loading file");
    }

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Brian\nWhat can I do for you?");
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public void foundTasks(List<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        showTasks(tasks);
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarked(Task task) {
        System.out.println("Okay! I've marked this task as not done yet");
        System.out.println(task);

    }

    public void showHelpCommand() {
    }

    public void showTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void removeTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);

    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
