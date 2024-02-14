import java.util.Scanner;
import java.util.ArrayList;

public class Damon {
    private static ArrayList<Task> storage;

    Damon() {
        storage = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        Damon damon = new Damon();
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);

        //Solution below adapted from https://stackoverflow.com/a/16252290
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")) {
                stop();
                break;
            } else if (inputString.equals("list")) {
                showList();
            } else if (inputString.startsWith("mark")) {
                int index = Integer.parseInt(inputString.substring(5)) - 1;
                mark(index);
            } else if (inputString.startsWith("unmark")) {
                int index = Integer.parseInt(inputString.substring(7)) - 1;
                unmark(index);
            } else {
                storeInput(inputString);
            }
        }
    }

    static void stop() {
        System.out.println("__________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "__________________________________________________________\n");
    }

    static void showList () {
        int n = storage.size();
        System.out.println("__________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        for (int i = 0; i < n; i++) {
            Task currentTask = storage.get(i);
            System.out.println((i + 1) + "." + "[" + storage.get(i).getStatusIcon() + "] "
                    + currentTask.description + "\n");
        }
        System.out.println("__________________________________________________________\n");
    }

    static void storeInput(String inputString) {
        storage.add(new Task(inputString));
        System.out.println("__________________________________________________________\n"
                + "add: " + inputString + "\n"
                + "__________________________________________________________\n");
    }

    static void echo(String inputString) {
            System.out.println("__________________________________________________________\n"
                    + inputString + "\n"
                    + "__________________________________________________________\n");
    }

    static void mark(int index) {
        storage.get(index).markAsDone();
        System.out.println("__________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + "  [" + storage.get(index).getStatusIcon() + "] "
                + storage.get(index).description + "\n"
                + "__________________________________________________________\n");
    }

    static void unmark(int index) {
        storage.get(index).markBackNotDone();
        System.out.println("__________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + "  [" + storage.get(index).getStatusIcon() + "] "
                + storage.get(index).description + "\n"
                + "__________________________________________________________\n");
    }
}
