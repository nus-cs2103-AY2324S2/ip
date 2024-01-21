import java.util.ArrayList;
import java.util.Scanner;

public class Dude {
    static final String spacer = "____________________________________________________________\n";
    static final String name = "Dude";
    private ArrayList<Task> list = new ArrayList<>();
    private static void print(String text) {
        System.out.println(spacer + text + spacer);
    }

    private static void greeting() {
        print("Hello! I'm Dude\nWhat can I do for you?\n");
    }
    private static void goodbye() {
        print("Bye. Hope to see you again soon!\n");
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void echo(String input) {
        System.out.println(spacer + input + "\n" + spacer);
    }

    private void add(String input) {
        this.list.add(new Task(input));
        print("added: " + input + "\n");
    }

    private void list() {
        String listString = "";
        for (int i = 1; i < this.list.size() + 1; i++) {
            Task task = this.list.get(i - 1);
            listString += i + "." + task + "\n";
        }
        print(listString);
    }

    private void mark(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid mark command");
            return;
        }
        String indexString = args[1];
        if (!isNumeric(indexString)) {
            System.out.println("Index is not numeric");
            return;
        }
        int index = Integer.parseInt(indexString);
        if (index > this.list.size()) {
            System.out.println("Index out of range");
            return;
        }
        Task task = this.list.get(index - 1);
        task.mark();
        print("Nice! I've marked this task as done:\n" + task + "\n");
    }

    private void unmark(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid unmark command");
            return;
        }
        String indexString = args[1];
        if (!isNumeric(indexString)) {
            System.out.println("Index is not numeric");
            return;
        }
        int index = Integer.parseInt(indexString);
        if (index > this.list.size()) {
            System.out.println("Index out of range");
            return;
        }
        Task task = this.list.get(index - 1);
        task.unmark();
        print("OK, I've marked this task as not done yet:\n" + task + "\n");

    }

    public static void main(String[] args) {
        greeting();

        Dude dude = new Dude();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();

            String[] ipArgs = input.split(" ");
            String command = ipArgs[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                dude.list();
            } else if (command.equals("mark")) {
                dude.mark(ipArgs);
            } else if (command.equals("unmark")) {
                dude.unmark(ipArgs);
            } else {
                dude.add(input);
            }
        }
        goodbye();
    }
}
