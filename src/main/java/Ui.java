import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void greet() {
        System.out.println("Hello! I'm HeadCube\n" + "What can I do for you?\n");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public void error(String message) {
        System.out.println(message);
    }

    public void toDoMessage() {
        System.out.println();
    }

    public void saveMessage() {
        System.out.println("Finished saving");
    }

    public void list(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        System.out.println();
    }

}
