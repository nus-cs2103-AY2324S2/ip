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
            } else if (input.contains("delete")) {
                String[] parts = input.split("\\s+", 2);
                delete(parts[1]);
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }

    public void greet() {
        System.out.println(LINE);
        System.out.println("Heyoo I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye lol see you again!");
        System.out.println(LINE);
        tasklist.saveTasks();
    }

    public void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    public void listTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list: ");
        tasklist.listTask();
        System.out.println(LINE);
    }

    public void addTask(String input) {
        System.out.println(LINE);

        boolean added = tasklist.addTask(input);
        int tasksCount = TaskList.tasksCount;

        if (added) {
            System.out.println("Gotcha. I've added this task: ");
            System.out.println("    " + tasklist.getTaskDescription(tasksCount - 1));
            System.out.println("Now you have " + tasksCount + " tasks in the list.");
        }
        System.out.println(LINE);
    }

    public void mark(String input, boolean status) {
        int num = Integer.parseInt(input) - 1; // Task num starts from 0
        tasklist.markTask(num, status);

        System.out.println(LINE);
        System.out.println(status ? "Good job on finishing this task: " : "Aw OK, I've marked this task as not done yet: ");
        System.out.println("    " + tasklist.getTaskDescription(num));
        System.out.println(LINE);
    }

    public void delete(String input) {
        int num = Integer.parseInt(input) - 1; // Task num starts from 0
        String deletedTask = tasklist.getTaskDescription(num);
        tasklist.deleteTask(num);

        System.out.println(LINE);
        System.out.println("Sure, I've removed this task:");
        System.out.println("    " + deletedTask);
        System.out.println("Now you have " + TaskList.tasksCount + " tasks in the list.");
        System.out.println(LINE);
    }
}
