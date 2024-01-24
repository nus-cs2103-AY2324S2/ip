import java.util.Scanner;

public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private Scanner scanner;
    private String[] tasks;
    private int id;

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.tasks = new String[100];
        this.id = 0;
    }

    public void run() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hey there! This is Chitty-Chatty\n" + "What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    exit();
                    break;
                case "list":
                    listTasks();
                    break;
                default:
                    addTasks(input);
                    break;
            }
        }


    }

    // add task to the list and print out the current task
    public void addTasks(String task) {
        tasks[id] = task;
        id++;
        System.out.println("added: " + task);
    }

    // list out all the tasks being added to the list
    public void listTasks() {
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

