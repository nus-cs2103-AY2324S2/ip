import java.util.Scanner;

public class Duke {
    private String name;
    private String[] tasks = new String[100];
    private int counter = 0;

    public Duke(String name) {
        this.name = name;
    }
    public void greetings() {
        String reply = "Hello! I'm " + this.name + ". \n"
                + "What can I do for you?";
        System.out.println(reply);
    }

    public void quitApplication() {
        String reply = "Bye. Hope to see you again soon!";
        System.out.println(reply);
    }

    public void addTask(String task) {
        tasks[counter++] = task;
        System.out.println("added: " + task);
    }

    public void printTasks() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name for your chatbot.");
        String name = scanner.nextLine();

        Duke duke = new Duke(name);
        duke.greetings();

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                duke.quitApplication();
                break;
            } else if (command.equals("list")) {
                duke.printTasks();
            } else {
                duke.addTask(command);
            }
        }
    }
}
