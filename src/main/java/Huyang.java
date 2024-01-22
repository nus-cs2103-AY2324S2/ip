import java.util.ArrayList;
import java.util.Scanner;

public class Huyang {
    private String logo =
            "  ___ ___                                      \n" +
                    " /   |   \\ __ __ ___.__._____    ____    ____  \n" +
                    "/    ~    \\  |  <   |  |\\__  \\  /    \\  / ___\\ \n" +
                    "\\    Y    /  |  /\\___  | / __ \\|   |  \\/ /_/  >\n" +
                    " \\___|_  /|____/ / ____|(____  /___|  /\\___  / \n" +
                    "       \\/        \\/          \\/     \\//_____/  ";

    private ArrayList<Task> tasks = new ArrayList<>();
    public void greet() {
        System.out.println("Hello! I'm Huyang");
        System.out.println(logo);
        System.out.println("What can I do for you?");
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                mark(input);
            } else if (input.startsWith("unmark ")) {
                unmark(input);
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    private void mark(String input) {
        int taskNumber = Integer.parseInt(input.substring(5));
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.check();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number");
        }
    }

    private void unmark(String input) {
        int taskNumber = Integer.parseInt(input.substring(7));
        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.uncheck();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
        } else {
            System.out.println("Invalid task number");
        }
    }
    public void farewell() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Huyang huyang = new Huyang();

        huyang.greet();
        huyang.echo();
        huyang.farewell();
    }
}


