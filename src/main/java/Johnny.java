import java.util.ArrayList;
import java.util.Scanner;

public class Johnny {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Johnny here. What do you want?\n");
        Johnny.takeCommands();
        System.out.println("Bye. I'm going back to sleep.");
    }

    public static void takeCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                Johnny.list();
            } else if (command.split(" ")[0].equals("mark")) {
                Johnny.mark(command);
            } else if (command.split(" ")[0].equals("unmark")) {
                Johnny.unmark(command);
            } else {
                Johnny.add(command);
            }
        }

        scanner.close();
    }

    public static void list() {
        for (int i = 0; i < Johnny.list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println();
    }

    public static void mark(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        Task task = Johnny.list.get(index);
        task.mark();
        System.out.println("Finally done.");
        System.out.println(task);
        System.out.println();
    }

    public static void unmark(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        Task task = Johnny.list.get(index);
        task.unmark();
        System.out.println("Can you hurry it up?");
        System.out.println(task);
        System.out.println();
    }

    public static void add(String command) {
        Task task = new Task(command);
        Johnny.list.add(task);
        System.out.println("added: " + command + "\n");
    }

}
