import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    Ui() {
        //Solution below adapted from https://stackoverflow.com/a/16252290
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showAddTask(Task newTask, TaskList tasks) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }

    public void showDeleteTask(int index, TaskList tasks) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + tasks.get(index).toString()
                + "Now you have " + (tasks.size() - 1) + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }

    public void showExit() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");
    }

    public void showMarkTask(TaskList tasks, int index) {
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + tasks.get(index).toString() + "\n"
                + "____________________________________________________________\n");
    }

    public void showUnMarkTask(TaskList tasks, int index) {
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + tasks.get(index).toString() + "\n"
                + "____________________________________________________________\n");
    }

    public void showTaskList(TaskList tasks) {
        int n = tasks.size();
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        for (int i = 0; i < n; i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1) + "." + currentTask.toString() + "\n");
        }
        System.out.println("____________________________________________________________\n");
    }

    public void showEcho(String inputString) {
        System.out.println("____________________________________________________________\n"
                + inputString + "\n"
                + "____________________________________________________________\n");
    }

    public void showLoadingError() {
        System.out.println("Something wrong happens.");
    }
}
