import java.util.Scanner;

public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scanner;

    private Task[] tasks;

    private int id;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.tasks = new Task[100];
        this.id = 0;
    }

    public void run() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hey there! This is Chitty-Chatty\n" + "What can I do for you?\n");

        while (true) {
            String[] input = scanner.nextLine().split(" ");
            String command = input[0];

            switch (command) {
                case "bye":
                    exit();
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTaskAsDone(input);
                    break;
                case "unmark":
                    markTaskAsUndone(input);
                    break;
                default:
                    addTasks(String.join(" ", input));
                    break;
            }
        }
    }

    public void markTaskAsDone(String[] input) {
        int index = Integer.parseInt(input[1]);
        tasks[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1].toString());
    }

    public void markTaskAsUndone(String[] input) {
        int index = Integer.parseInt(input[1]);
        tasks[index - 1].markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index - 1].toString());
    }

    public void addTasks(String task) {
        tasks[id] = new Task(task);
        id++;
        System.out.println("added: " + task);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < id; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    public void exit() {
        System.out.println("Goodbye. Have a great day ahead!");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

