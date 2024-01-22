import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private String name;
    private ArrayList<Task> tasks = new ArrayList<Task>();

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

    public void addTask(String description) {
        tasks.add(new Task(description));
        System.out.println("added: " + description);
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            System.out.println(i + 1 + ".[" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }

    public void changeTaskStatus(int taskNum, boolean status) {
        int index = taskNum - 1;
        Task task = tasks.get(index);
        task.setStatusIcon(status);
        if (status) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name for your chatbot.");
        String name = scanner.nextLine();

        Duke duke = new Duke(name);
        duke.greetings();

        while (true) {
            String input = scanner.nextLine();
            String[] split = input.split(" ", 0);

            String command = split[0];
            int taskNum = -1;
            if (split.length > 1) {
                taskNum = Integer.parseInt(split[1]);
            }

            switch(command) {
                case "bye":
                    duke.quitApplication();
                    return;
                case "list":
                    duke.printTasks();
                    break;
                case "mark":
                    duke.changeTaskStatus(taskNum, true);
                    break;
                case "unmark":
                    duke.changeTaskStatus(taskNum, false);
                    break;
                default:
                    duke.addTask(command);
                    break;
            }
        }
    }
}
