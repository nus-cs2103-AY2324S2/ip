import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
    static void breakLine() {
        System.out.println("---------------------------------------");
    }
    static void greet() {
        System.out.println("Hello! I'm teletubby"  + "\nWhat can I do for you?");
        breakLine();
    }
    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        breakLine();
    }
    static void echo() {

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                Duke.breakLine();
                Duke.exit();
                break;
            } else {
                Duke.breakLine();
                System.out.println(input);
                Duke.breakLine();
            }
        }

        sc.close();
    }

    static void add(String input) {
        Duke.breakLine();
        Task t = new Task(input);
        tasks.add(t);
        System.out.println("added: " + input);
        Duke.breakLine();
    }
    static void list() {
        Duke.breakLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("%d.[%s] %s\n", i + 1, t.getStatusIcon(), t.getDescription());
        }
        Duke.breakLine();
    }

    public static void main(String[] args) {
        Duke.breakLine();
        Duke.greet();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Duke.breakLine();
                Duke.exit();
                break;
            } else if (input.equals("list")) {
                Duke.list();
            } else if (input.startsWith("mark")) {
                Duke.breakLine();
                int taskNumber = (int) input.charAt(input.length() - 1) - (int) '0';
                tasks.get(taskNumber-1).markAsDone();
                Duke.breakLine();
            } else if (input.startsWith("unmark")) {
                Duke.breakLine();
                int taskNumber = (int) input.charAt(input.length() - 1) - (int) '0';
                tasks.get(taskNumber-1).unmark();
                Duke.breakLine();
            } else {
                Duke.add(input);
            }
        }
    }
}
