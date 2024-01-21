import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            } else if (command.split(" ")[0].equals("todo")){
                Johnny.addToDo(command);
            } else if (command.split(" ")[0].equals("deadline")) {
                Johnny.addDeadline(command);
            } else if (command.split(" ")[0].equals("event")) {
                Johnny.addEvent(command);
            }
        }

        scanner.close();
    }

    public static void list() {
        System.out.println("Get all these done:");
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
        System.out.println(task + "\n");
    }

    public static void unmark(String command) {
        int index = Integer.parseInt(command.split(" ")[1]) - 1;
        Task task = Johnny.list.get(index);
        task.unmark();
        System.out.println("Can you hurry it up?");
        System.out.println(task + "\n");
    }

    public static void addToDo(String command) {
        List<String> l = Arrays.asList(command.split(" "));
        Task task = new ToDo(String.join(" ", l.subList(1, l.size())));
        Johnny.list.add(task);
        System.out.println("Go get this done:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks to do.\n");
    }

    public static void addDeadline(String command) {
        List<String> l = Arrays.asList(command.split(" "));
        int i = l.indexOf("/by");
        Task task = new Deadline(String.join(" ", l.subList(1, i)),
                String.join(" ", l.subList(i + 1, l.size())));
        Johnny.list.add(task);
        System.out.println("Go get this done:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks to do.\n");
    }

    public static void addEvent(String command) {
        List<String> l = Arrays.asList(command.split(" "));
        int i = l.indexOf("/from");
        int j = l.indexOf("/to");
        Task task = new Event(String.join(" ", l.subList(1, i)),
                String.join(" ", l.subList(i + 1, j)),
                String.join(" ", l.subList(j + 1, l.size())));
        Johnny.list.add(task);
        System.out.println("Go get this done:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks to do.\n");
    }

}
