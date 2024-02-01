package duke;

import duke.task.Task;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String name = "Detective";
    private static final String line = "____________________________________________________________";

    private final Scanner in = new Scanner(System.in);
    private final PrintStream out = System.out;

    public String getCommand() {
        return in.nextLine();
    }

    public void showWelcome() {
        showToUser("Hello! I'm [" + name + "]", "What can I do for you? ovo");
    }

    public void showBye() {
        showToUser("Bye. Hope to see you again soon! >v<");
    }

    public void showList(TaskList taskList) {
        out.println(line);
        out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            out.println((i + 1) + "." + taskList.get(i));
        }
        out.println(line);
    }

    public void showMark(TaskList taskList, int taskNum) {
        showToUser("Nice! I've marked this detective.task as done:", taskList.get(taskNum).toString());
    }

    public void showUnmark(TaskList taskList, int taskNum) {
        showToUser("Nice! I've unmarked this detective.task as done:", taskList.get(taskNum).toString());
    }

    public void showAddTask(TaskList taskList, Task task) {
        showToUser("Got it. I've added this detective.task:", task.toString(), "Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showDeleteTask(TaskList taskList, Task task) {
        showToUser("Got it. I've deleted this detective.task:", task.toString(), "Now you have " + taskList.size() + " tasks in the list.");
    }

    public void showError(String message) {
        showToUser("Error: " + message);
    }

    public void showToUser(String... message) {
        out.println(line);
        for (String m : message) {
            out.println(m);
        }
        out.println(line);
    }
}
