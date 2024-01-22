import java.util.Scanner;

public class Chatbot {

    private final String name;
    private final static String LINE = "--------------------------------------------------------";
    private TaskList tasklist; // Hold the list of tasks

    public Chatbot(String name) {
        this.name = name;
        this.tasklist = new TaskList();
    }

    public void startChat() {
        Scanner scanner = new Scanner(System.in);

        greet();

        boolean isChatting = true;
        while (isChatting) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                bye();
                isChatting = false;
            } else if (input.contains("list")) {
                listTasks();
            } else if (input.contains("mark")) {
                String[] parts = input.split("\\s+", 2);
                mark((parts[1]), !parts[0].contains("un"));
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }

    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    public void listTasks() {
        System.out.println(LINE);
        tasklist.listTask();
        System.out.println(LINE);
    }

    public void addTask(String input) {
        System.out.println(LINE);

        boolean added = tasklist.addTask(input);
        int tasksCount = TaskList.tasksCount;

        if (added) {
            System.out.println("Got it. I've added this task: ");
            System.out.println("    " + tasklist.getTaskDescription(tasksCount - 1));
            System.out.println("Now you have " + tasksCount + " tasks in the list.");
        }
        System.out.println(LINE);
    }

    public void mark(String input, boolean status) {
        int num = Integer.parseInt(input) - 1; // Task num starts from 0
        tasklist.markTask(num, status);

        System.out.println(LINE);
        System.out.println(status ? "Nice! I've marked this task as done: " : "OK, I've marked this task as not done yet: ");
        System.out.println("    " + tasklist.getTaskDescription(num));
        System.out.println(LINE);
    }
}
