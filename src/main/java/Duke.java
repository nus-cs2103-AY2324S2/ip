import java.util.Scanner;

public class Duke {
    private final static String[] tasks =  new String[100];
    private static int count = 0;
    public static void main(String[] args) {
        greetUser();
        echo();
        exit();
    }
    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Judy");
        System.out.println(" What can I do for you?");
        System.out.println("________________________________________");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String  command;

        do {
            System.out.print("\n");
            command = scanner.nextLine();
            if(command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listOutTasks();
            } else {
                addTask(command);
            }
        } while (true);

        scanner.close();
    }

    private static void addTask(String task) {
        tasks[count++] = task;
        System.out.println("_________________________________________");
        System.out.println("added: " + task);
        System.out.println("_________________________________________");
    }

    private static void listOutTasks() {
        System.out.println("_________________________________________");
        if (count == 0) {
            System.out.println("No task added");
        } else {
            for(int i = 0; i < count; i++) {
                int id = i+1;
                System.out.print(" " + id + ". " + tasks[i] + "\n");
            }
        }
        System.out.println("_________________________________________");
    }

    private static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }
}
