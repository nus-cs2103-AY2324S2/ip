import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        ArrayList<Task> list = new ArrayList<>();

        // Start-up introduction
        printBreak();
        System.out.println("Hello! I'm Klara");
        System.out.println("What can I do for you?");
        printBreak();

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        while (!word.equals("bye")) {
            // print list on demand
            if (word.equals("list")) {
                printList(list);
                word = sc.nextLine();
            } else if (word.startsWith("mark")) {
                String[] split = word.split(" ");
                Task extractedTask = list.get(Integer.valueOf(split[1]) - 1);
                extractedTask.markAsDone();
                printBreak();
                System.out.println(extractedTask);
                printBreak();
                word = sc.nextLine();
            } else if (word.startsWith("unmark")) {
                String[] split = word.split(" ");
                Task extractedTask = list.get(Integer.valueOf(split[1]) - 1);
                extractedTask.markAsUndone();
                printBreak();
                System.out.println(extractedTask);
                printBreak();
                word = sc.nextLine();
            } else {
                Task task = new Task(word);
                list.add(task);
                printBreak();
                System.out.println("added: " + word);
                printBreak();
                word = sc.nextLine();
            }
        }

        // Logging off upon "bye" command
        printBreak();
        System.out.println("Bye. Hope to see you again soon!");
        printBreak();
    }


    private static void printBreak() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Print all elements in the list
      * @param list
     */
    private static void printList(ArrayList<Task> list) {
        printBreak();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDone) {
                System.out.println(Integer.toString(i + 1) + ".[X] " + list.get(i).description);
            }
            else {
                System.out.println(Integer.toString(i + 1) + ".[ ] " + list.get(i).description);
            }
        }
        printBreak();
    }


}
