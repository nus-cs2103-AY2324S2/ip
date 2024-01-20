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
        List<String> textList = new ArrayList<String>();

        greet();

        // Active
        while (true){
            // Take user input
            input = scanner.nextLine();  // Read user input

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                list(textList);
            } else {
                echo(input);
                textList.add(input);
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

    public static void list(List<String> list) {
        String text;
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            text = "\t" + (i+1) + ". " + list.get(i);
            System.out.println(text);
        }
        System.out.println("\t____________________________________________________________\n");
    }
}

