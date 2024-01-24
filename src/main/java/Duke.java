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

    private void add(String input) {
        this.list.add(new Task(input));
        System.out.println("\tadded: " + input);
        System.out.println(LINE);
    }

    private void list() {
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
                continue;
            } else if (input.startsWith("mark")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                duke.list.get(index).markAsDone();
                continue;
            } else if (input.startsWith("unmark")){
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                duke.list.get(index).markNotDone();
                continue;
            }
            duke.add(input);
        }
        duke.sayBye();
    }
}
