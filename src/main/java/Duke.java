import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Task> list = new ArrayList<Task>();
    private String name = "NotDuke";

    private void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    private void input() {
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            return;
        }

        if (input.equals("list")) {
            list();
        } else if (input.startsWith("unmark")) {
            int index = Integer.valueOf(input.substring(7));
            if (index > list.size()) {
                System.out.println("There is no task at " + index);
            } else {
                Task task = list.get(index - 1);
                task.unmark();
            }
        } else if (input.startsWith("mark")) {
            int index = Integer.valueOf(input.substring(5));
            if (index > list.size()) {
                System.out.println("There is no task at " + index);
            } else {
                Task task = list.get(index - 1);
                task.mark();
            }
        } else {
            add(input);
        }
        input();
    }

    private void add(String input) {
        Task task;
        if (input.startsWith("todo")) {
            String name = input.substring(5);
            task = new ToDos(name);
        } else if (input.startsWith("deadline")) {
            String name = input.substring(9, input.indexOf(" /by"));
            String by = input.substring(input.indexOf(" /by") + 5);
            task = new Deadlines(name, by);
        } else if (input.startsWith("event")) {
            String name = input.substring(6, input.indexOf(" /from"));
            String from = input.substring(input.indexOf(" /from") + 7, input.indexOf(" /to"));
            String to = input.substring(input.indexOf(" /to") + 5);
            task = new Events(name, from, to);
        } else {
            System.out.println("Invalid Format");
            return;
        }

        list.add(task);
        int length = list.size();
        System.out.println("Got it. I've added this task:");
        task.taskInfo();
        System.out.print("Now you have " + length + " task");
        if (length > 1) {System.out.print("s");}
        System.out.println(" in the list");
    }

    private void list() {
        if (list.size() == 0) {
            System.out.println("The List is Empty");
        }
        int index = 0;
        for (Task item : list) {
            System.out.print(++index + ".");
            item.taskInfo();
        }

    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.input();
        bot.exit();
    }
}

