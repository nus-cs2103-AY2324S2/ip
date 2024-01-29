import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String isDone(ArrayList<Boolean> check, int index) {
        if (check.get(index)) {
            return "[X]";
        } else {
            return "[ ]";

        }
    }
    private static void markTask(ArrayList<Boolean> check, int index, boolean done) {
        check.set(index, done);
    }

    public static void main(String[] args) {
        ArrayList<String> storage = new ArrayList<>();
        ArrayList<Boolean> marked_checker = new ArrayList<>();
        String logo = "__________________________________\n" +
                "Hello! I'm Tim \n" +
                "What can i do for you? \n" +
                "__________________________________\n";
        String exit = "Bye. Hope to see you again soon!\n" +
                "__________________________________";

        System.out.println(logo);

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                System.out.println("__________________________________\n" +
                        "Here are the tasks in your list:\n");
                for (int i = 0; i < storage.size(); i++) {
                    int counter = i + 1;
                    String done = isDone(marked_checker, i);
                    String task = storage.get(i);
                    String output = String.format("%d. %s %s", counter, done, task);
                    System.out.println(output);
                }
            } else if (input.startsWith("mark")) {
                String[] temp = input.split(" ");
                int index = Integer.parseInt(temp[1]) - 1;
                markTask(marked_checker, index, true);
                String output = "Nice! I've marked this task as done:\n" +
                        "   " + isDone(marked_checker,index) + " " + storage.get(index);
                System.out.println(output);

            } else if (input.startsWith("unmark")) {
                String[] temp = input.split(" ");
                int index = Integer.parseInt(temp[1]) - 1;
                markTask(marked_checker, index, false);
                String output = "OK, I've marked this task as not done yet:\n" +
                        "   " + isDone(marked_checker, index) + " " + storage.get(index);
                System.out.println(output);

            } else {
                String msg = "__________________________________\n" +
                        "added:" + input;
                System.out.println(msg);
                marked_checker.add(false);
                storage.add(input);
            }
            System.out.println("__________________________________\n");
            input = scan.nextLine();

        }

    }
}
