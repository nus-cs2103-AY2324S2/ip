import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "\t____________________________________________________________";

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();


    private void sayGreetings() {
        System.out.println(LINE);
        System.out.println("\tHello! I'm SKY");
        System.out.println("\tWhat can I do for you?");
        System.out.println(LINE);
    }

    private void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void echo(String input) {
        System.out.println(input);
        System.out.println(LINE);
    }

    private void add(Task task) {
        this.list.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + this.list.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    private void list() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.list.get(i));
        }
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.sayGreetings();

        while (true) {
            String input = duke.sc.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                duke.list();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                duke.list.get(index).markAsDone();
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                duke.list.get(index).markNotDone();
            } else if (input.startsWith("todo")) {
                input = input.replaceFirst("todo ", "");
                duke.add(new Todo(input));
            } else if (input.startsWith("deadline")) {
                input = input.replaceFirst("deadline ", "");
                String[] arr = input.split(" /by ");
                duke.add(new Deadline(arr[0], arr[1]));
            } else if (input.startsWith("event")) {
                input = input.replaceFirst("event ", "");
                String[] arr = input.split(" /from ");
                String[] arr1 = arr[1].split(" /to ");
                duke.add(new Event(arr[0], arr1[0], arr1[1]));
            } else {
                System.out.println("\tI don't understand what you mean :(");
                System.out.println(LINE);
            }
        }
        duke.sayBye();
    }
}
