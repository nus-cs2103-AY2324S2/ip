import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final String horizontalLine = "____________________________________________________________\n";
    private final String name;

    private final ArrayList<String> tasks;

    public Duke(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void greetUser() {
        System.out.println(this.horizontalLine
                + "Hello! I'm "
                + this.name
                + "\n"
                + "What can I do for you?\n"
                + this.horizontalLine);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n" + this.horizontalLine);
    }

    public void echo() {

        Scanner myScannerObj = new Scanner(System.in);
        System.out.print("Message Zenify: ");
        while (myScannerObj.hasNext()) {
            System.out.print(this.horizontalLine);
            String task = myScannerObj.nextLine();
            if (task.equalsIgnoreCase("bye")) {
                this.exit();
                break;
            } else if (task.equalsIgnoreCase("list")) {
                this.toDoList();
            } else {
                this.addToDo(task);
            }
            System.out.print(this.horizontalLine);
            System.out.print("\nMessage Zenify: ");
        }

        myScannerObj.close();

    }

    private void addToDo(String task) {
        tasks.add(task);
        System.out.println(" added: " + task);
    }

    private void toDoList() {
        if (tasks.isEmpty()) {
            System.out.println(" Please add task!");
        } else {
            int taskCount = 0;
            for (String task : tasks) {
                taskCount++;
                System.out.println(" " + taskCount + ". " + task);
            }
        }
    }

    public static void main(String[] args) {

        Duke zenifyBot = new Duke("Zenify");
        zenifyBot.greetUser();
        zenifyBot.echo();

    }
}
