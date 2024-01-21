import java.util.Scanner;
public class HeadCube {
    private static String[] tasks = new String[100];
    private static int taskCount = 0;
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                list();
            } else {
                add(input);
            }

        }
    }

    public static void greet() {
        System.out.println("Hello! I'm HeadCube\n" + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void echo(String input) {
        System.out.println(input + "\n");
    }

    public static void add(String task) {
        tasks[taskCount] = task;
        taskCount++;
        System.out.println("added: " + task + "\n");
    }

    public static void list() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println();
    }
}
