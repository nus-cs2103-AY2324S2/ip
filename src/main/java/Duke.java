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

    public void addTask(String input) {
        String[] splitInput = input.split(" ", 2);
        String type = splitInput[0];

        String[] info = splitInput[1].split("/");
        String description = info[0];

        switch(type) {
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "deadline":
                String by = info[1].replaceAll("by", "").trim();
                tasks.add(new Deadline(description, by));
                break;
            case "event":
                String from = info[1].replaceAll("from", "").trim();
                by = info[2].replaceAll("to", "").trim();
                tasks.add(new Event(description, from, by));
                break;
        }

        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());
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
            String command = input.split(" ", 0)[0];

            switch(command) {
                case "bye":
                    duke.quitApplication();
                    return;
                case "list":
                    duke.printTasks();
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                    duke.changeTaskStatus(taskNum, true);
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                    duke.changeTaskStatus(taskNum, false);
                    break;
                default:
                    duke.addTask(input);
                    break;
            }
        }
    }
}
