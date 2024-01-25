import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadGPT {
    public static void main(String[] args) {
        String name = "BadGPT";
        List<String> tasks = new ArrayList<>(100);

        line();
        System.out.println("Hello! I'm " + name + ".\n" + "What can I do for you?");
        line();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String cmd = sc.next();

            // If user enters "bye", exit the loop and the program.
            // If user enters "list", list out all currently stored tasks.
            // Else, store the string entered as a task.
            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")){
                list(tasks);
            } else {
                String task = cmd + sc.nextLine();
                store(task, tasks);
            }
        }

        bye();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void store(String task, List<String> tasks) {
        tasks.add(task);
        line();
        System.out.println("Added: " + task);
        line();
    }

    public static void list(List<String> tasks) {
        line();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        line();
    }

    public static void bye() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
        System.exit(0);
    }
}
