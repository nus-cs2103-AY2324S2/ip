import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner scanner = new Scanner(System.in);
    ArrayList<String> todo = new ArrayList<String>();
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
        } else {
            add(input);
        }
        input();
    }

    private void add(String input) {
        todo.add(input);
        System.out.println("added: " + input);
    }

    private void list() {
        int index = 0;
        for (String item : todo) {
            System.out.println(++index + ": " + item);
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
