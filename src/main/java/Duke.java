import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner scanner = new Scanner(System.in);
    ArrayList<Task> todo = new ArrayList<Task>();
    private String name = "NotDuke";

    private void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    private void input() {
        String input = scanner.next();
        if (input.equals("bye")) {
            return;
        }

        if (input.equals("list")) {
            list();
        } else if (input.equals("unmark")) {
            String index = scanner.next();
            Task task = todo.get(Integer.valueOf(index) - 1);
            task.unmark();
        } else if (input.equals("mark")) {
            String index = scanner.next();
            Task task = todo.get(Integer.valueOf(index) - 1);
            task.mark();
        } else {
            add(input);
        }
        input();
    }

    private void add(String input) {
        todo.add(new Task(input));
        System.out.println("added: " + input);
    }

    private void list() {
        int index = 0;
        for (Task item : todo) {
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

