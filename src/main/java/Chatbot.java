import java.util.Scanner;

public class Chatbot {

    private final String name;
    private final static String LINE = "----------------------------------";
    private ToDo todo; // Hold the list of tasks

    public Chatbot(String name) {
        this.name = name;
        this.todo = new ToDo();
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
            } else if (input.equalsIgnoreCase("list")) {
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
        todo.listTask();
        System.out.println(LINE);
    }

    public void addTask(String input) {
        Task task = new Task(ToDo.tasksCount, input);
        todo.addTask(task);

        System.out.println(LINE);
        System.out.println("added: " + input);
        System.out.println(LINE);
    }

    public void mark(String input, boolean status) {
        int num = Integer.parseInt(input) - 1; // Task num starts from 0
        todo.markTask(num, status);

        System.out.println(LINE);

        if (status) {
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            System.out.println("OK, I've marked this task as not done yet: ");
        }

        System.out.println(todo.getTask(num).getDescription());
        System.out.println(LINE);
    }
}
