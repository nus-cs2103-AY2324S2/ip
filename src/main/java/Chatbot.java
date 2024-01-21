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
        Task task = new Task(input);
        todo.addTask(task);

        System.out.println(LINE);
        System.out.println("added: " + input);
        System.out.println(LINE);
    }
}
