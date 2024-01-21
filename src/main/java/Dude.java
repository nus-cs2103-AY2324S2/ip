import java.util.ArrayList;
import java.util.Arrays;
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

    private void add(Task task) {
        this.list.add(task);
        print("Got it. I've added this task:\n" + task + "\nNow you have " + this.list.size() + " tasks in the list.\n");
    }

    private void list() {
        String listString = "";
        for (int i = 1; i < this.list.size() + 1; i++) {
            Task task = this.list.get(i - 1);
            listString += i + "." + task + "\n";
        }
        print(listString);
    }

    private void mark(String args) {
//        if (args.length != 2) {
//            System.out.println("Invalid mark command");
//            return;
//        }
//        if (!isNumeric(indexString)) {
//            System.out.println("Index is not numeric");
//            return;
//        }
        int index = Integer.parseInt(args);
//        if (index > this.list.size()) {
//            System.out.println("Index out of range");
//            return;
//        }
        Task task = this.list.get(index - 1);
        task.mark();
        print("Nice! I've marked this task as done:\n" + task + "\n");
    }

    private void unmark(String args) {
//        if (args.length != 2) {
//            System.out.println("Invalid unmark command");
//            return;
//        }
//        if (!isNumeric(indexString)) {
//            System.out.println("Index is not numeric");
//            return;
//        }
        int index = Integer.parseInt(args);
//        if (index > this.list.size()) {
//            System.out.println("Index out of range");
//            return;
//        }
        Task task = this.list.get(index - 1);
        task.unmark();
        print("OK, I've marked this task as not done yet:\n" + task + "\n");

    }

    public static void main(String[] args) {
        greeting();

        Dude dude = new Dude();

        Scanner scanner = new Scanner(System.in);
        loop:
        while(true) {
            String input = scanner.nextLine();

            String[] inputSplit = input.split(" ", 2);
            String command = inputSplit[0];
            String ipArgs = inputSplit.length > 1 ? inputSplit[1] : "";
            switch (command) {
                case "bye":
                    break loop;
                case "list":
                    dude.list();
                    break;
                case "mark":
                    dude.mark(ipArgs);
                    break;
                case "unmark":
                    dude.unmark(ipArgs);
                    break;
                case "todo":
                    Todo todo = new Todo(ipArgs);
                    dude.add(todo);
                    break;
                case "deadline": {
                    String[] ipArgsSplit = ipArgs.split("/");
                    Deadline deadline = new Deadline(ipArgsSplit[0], ipArgsSplit[1].replace("by ", ""));
                    dude.add(deadline);
                    break;
                }
                case "event": {
                    String[] ipArgsSplit = ipArgs.split("/");
                    Event event = new Event(
                            ipArgsSplit[0],
                            ipArgsSplit[1].replace("from ", ""),
                            ipArgsSplit[2].replace("to ", ""));
                    dude.add(event);
                    break;
                }
                default:
                    continue;
            }
        }
        goodbye();
    }
}
