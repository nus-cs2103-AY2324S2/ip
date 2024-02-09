import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showHello() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Your Only Friend\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public void showBye() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n - Your Only Friend\n"
                + "____________________________________________________________\n");
    }

    public void showError(String message) {

        System.out.println("____________________________________________________________\n" +
                message +
                "____________________________________________________________\n");
    }

    public String readCommand() {
        return scanner.nextLine().trim().toLowerCase();
    }

    public void showList(TaskList tasks) {
        System.out.println("____________________________________________________________\n");
        System.out.println(" Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(" " + i + "." + tasks.getTasks().get(i - 1).toString());
        }
        System.out.println("____________________________________________________________\n");}

    public void showTask(String msg, Task task) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg + "\n");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________\n");
    }

    public void showTaskWithNum(String msg, Task task, TaskList tasks) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg + "\n");
        System.out.println(task.toString());
        System.out.println(" Now you have " + tasks.getSize() + (tasks.getSize() <= 1 ? " task in the list." : " tasks in the list.\n"));
        System.out.println("____________________________________________________________\n");
    }
}
