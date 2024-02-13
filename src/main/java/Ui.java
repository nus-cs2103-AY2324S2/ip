import java.util.Scanner;

public class Ui {
    public Ui() {}

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Blob.");
        System.out.println("What can I do for you?\n");
    }

    public void showWrongFormat() {
        System.err.println("Wrong format! :(");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
        System.out.println();
    }

    public void showForgetTaskNumber() {
        System.err.println("You forgot to type which task!");
    }

    public void showMarkFormat() {
        System.out.println("Type: 'mark n' to mark the n-th task.");
        System.out.println("For example type: 'mark 1' to mark the first task.\n");
    }

    public void showUnmarkFormat() {
        System.out.println("Type: 'unmark n' to unmark the n-th task.");
        System.out.println("For example, type: 'unmark 1' to mark the first task.\n");
    }

    public void showDeleteFormat() {
        System.out.println("Type: 'delete n' to delete the n-th task.");
        System.out.println("For example type: 'delete 1' to delete the first task.\n");
    }

    public void showDateFormat() {
        System.out.println("The correct date format is: YYYY-MM-DD\n");
    }

    public void showToDoFormat() {
        System.out.println("The correct format is:");
        System.out.println("todo <description>\n");
    }

    public void showDeadlineFormat() {
        System.out.println("The correct format is:");
        System.out.println("deadline <description> /by <deadline time>\n");
    }

    public void showEventFormat() {
        System.out.println("The correct format is:");
        System.out.println("event <description> /from <start time> /to <end time>\n");
    }

    public void showWrongCommand() {
        System.out.println("You need to use 'todo', 'deadline' or 'event' command to add a task.");
        System.out.println("You can use 'list' to see all of your tasks.");
        System.out.println("Use 'mark' or 'unmark' for any of your tasks.\n");
    }

    public void showCreateTask(Task task, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.\n");
    }

    public void showNoTaskFound() {
        System.out.println("You don't have that task!\n");
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task done:");
        System.out.println(task + "\n");
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task + "\n");
    }

    public void showDeleteTask(Task task, int taskListSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.\n");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
