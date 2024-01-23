import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Dune");
        System.out.println("What can I do for you?");
        List<String> toDos = new ArrayList<>();
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("list")) {
                for (int i = 0; i < toDos.size(); i++) {
                    System.out.println("    " + i + ". " + toDos.get(i));
                }
                continue;
            }
            if (text.equals("bye")) {
                break;
            }
            toDos.add(text);
            System.out.println("    added:" + text);  // Output user input
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
