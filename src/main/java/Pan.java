import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Pan {
    public static List<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("");
            String instruction = scanner.nextLine();
            System.out.println("");

            if (instruction.equals("list")) {
                list();
                continue;
            } else if (instruction.equals("bye")) {
                bye();
                break;
            } else {
                tasks.add(new Task(instruction, false));
                add(instruction);
                continue;
            }
        }
        scanner.close();
    }

    public static void hello() {
        System.out.println("Hello! I'm Pan\nWhat can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void add(String instruction) {
        System.out.println("added: " + instruction + "\n");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    public static void mark() {

    }
}
