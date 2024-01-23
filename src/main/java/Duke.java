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
        List<Task> toDos = new ArrayList<>();
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("list")) {
                // print out tasks line by line
                for (int i = 0; i < toDos.size(); i++) {
                    System.out.println("    " + i + ". " + toDos.get(i));
                }
                continue;
            } else if (text.equals("bye")) {
                // exit program
                break;
            }

            // check if the first 4 characters matches mark
            String upTo4Characters = text.substring(0, Math.min(text.length(), 4));
            if (upTo4Characters.equals("mark")) {
                try {
                    String remaining = text.substring(4);
                    int start = 0;
                    // if the first character is blank space
                    // might throw IndexOutOfBounds exception
                    if (remaining.charAt(0) == ' ') {
                        start = 1;
                    }
                    // substring might throw IndexOutOfBounds exception
                    // parseInt might throw NumberFormatException
                    int index = Integer.parseInt(remaining.substring(start));
                    // Index... exception
                    toDos.get(index).complete();
                    System.out.println("    " + "Nice! I've marked this task as done:");
                    System.out.println("    " + toDos.get(index));

                } catch (IndexOutOfBoundsException i) {
                    System.out.println("    " + "Give a valid index to mark");
                } catch (NumberFormatException n) {
                    System.out.println("    " + "Remaining characters do not match an integer");
                } finally {
                    continue;
                }
            }

            // check if first 6 characters match unmark


            toDos.add(new Task(text));
            System.out.println("    added:" + text);  // Output user input
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
