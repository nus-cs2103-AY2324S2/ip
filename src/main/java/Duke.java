import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String input;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        List<Task> taskList = new ArrayList<Task>();

        greet();

        // Active
        while (true){
            // Take user input
            input = scanner.nextLine();  // Read user input
            int space = input.indexOf(" ");
            if (space == -1) {space = input.length()-1;}
            String firstWord = input.substring(0, space);
            String trail = input.substring(input.indexOf(" ") + 1);

            if (input.equals("bye")) {
                break;
                
            } else if (input.equals("list")) {
                list(taskList);
                
            } else if (firstWord.equals("mark")) {
                mark(taskList, trail);
            } else if (firstWord.equals("unmark")) {
                unmark(taskList, trail);
                
            } else if (firstWord.equals("todo")) {
                Task add = new ToDo(trail);
                add(taskList, add);
            } else if (firstWord.equals("deadline")) {
                Task add = new Deadline(
                        trail.substring(0, trail.indexOf(" /by ")),
                        trail.substring(trail.indexOf(" /by ") + 5));
                add(taskList, add);
            } else if (firstWord.equals("event")) {
                Task add = new Event(
                        trail.substring(0, trail.indexOf(" /from ")),
                        trail.substring(trail.indexOf(" /from ") + 7, trail.indexOf(" /to ")),
                        trail.substring(trail.indexOf(" /to ") + 5));
                add(taskList, add);
            } else {
                // echo(input);
                Task add = new Task(input);
                add(taskList, add);
            }
        }

        exit();
    }

    public static void greet() {
        String text = "\t____________________________________________________________\n"
                + "\tHello! I'm Teemo!\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void exit() {
        String text = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void echo(String text) {
        String output = "\t____________________________________________________________\n"
                + "\tadded: " + text + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(output);
    }

    public static void list(List<Task> list) {
        String text;
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            text = "\t" + (i+1) + "." + list.get(i).toString();
            System.out.println(text);
        }
        System.out.println("\t____________________________________________________________\n");
    }

    public static void mark(List<Task> list, String num) {
        Task curr = list.get(Integer.parseInt(num) - 1); // TODO error checking
        curr.mark();

        String text = "\t____________________________________________________________\n"
                + "\tNice! I've marked this task as done:\n"
                + "\t" + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);

    }

    public static void unmark(List<Task> list, String num) {
        Task curr = list.get(Integer.parseInt(num) - 1); // TODO error checking
        curr.unmark();

        String text = "\t____________________________________________________________\n"
                + "\tOK, I've marked this task as not done yet:\n"
                + "\t" + curr.toString() + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void add(List<Task> list, Task add) {
        list.add(add);
        String word = " task";
        if (list.size() != 1) {word += "s";}

        String text = "\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t  " + add.toString() + "\n"
                + "\tNow you have " + list.size() + word + " in the list.\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }
}

