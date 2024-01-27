import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();

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
            }
            else {
                list.add(word);
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
    private static void printList(ArrayList<String> list) {
        printBreak();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + list.get(i));
        }
        printBreak();
    }


}
