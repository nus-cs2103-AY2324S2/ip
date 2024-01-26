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
        Task taskAdded;
        if (input.startsWith("todo")) {
            ToDo td = new ToDo(input.substring(5));
            tasks.add(td);
            taskAdded = td;
        } else if (input.startsWith("deadline")) {
            String by = input.split("/by ", 2)[1];
            String description = input.split(" ", 2)[1].split(" /by")[0];
            Deadline d = new Deadline(description, by);
            tasks.add(d);
            taskAdded = d;
        } else if (input.startsWith("event")) {
            String description = input.split(" ", 2)[1].split(" /from")[0];
            String start = input.split("/from ",2)[1].split(" /to")[0];
            String end = input.split("/to ")[1];
            Event e = new Event(description, start, end);
            tasks.add(e);
            taskAdded = e;
        } else {
            return;
        }
        int numItems = tasks.size();
        String sOrP = numItems == 1 ? "task" : "tasks";
        System.out.println("Got it. I've added this task:\n" + taskAdded.toString() + "Now you have " + numItems + " " + sOrP +" in the list.");
        Duke.breakLine();
    }
    static void list() {
        Duke.breakLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("%d. %s", i+1, t.toString());
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
