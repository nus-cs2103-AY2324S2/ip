import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tony {
    public static void main(String[] args) {
        ToDoList lst = new ToDoList();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        greeting();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            String firstWord = words[0];
            if (firstWord.equals("list")) {
                lst.print();
            } else if (firstWord.equals("mark")) {
                String secondWord = words[1];
                lst.mark(secondWord);
            } else if (firstWord.equals("unmark")) {
                String secondWord = words[1];
                lst.unmark(secondWord);
            } else {
                lst.add(input);
            }
            input = scanner.nextLine();
        }
        goodbye();
    }

    private static void greeting() {
        String greeting = "_______________________\n"
                + "Hello! I'm Tony!\n"
                + "What can I do for you?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    private static void goodbye() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        line();
        System.out.println(goodbye);
        line();
        System.exit(0);
    }
    private static void line() {
        System.out.println("_______________________\n");
    }

    static class ToDoList {
        List<String> list = new ArrayList<>();
        List<String> done = new ArrayList<>();

        public void add(String item) {
            list.add(item);
            done.add(" ");
            line();
            System.out.println("Added: " + item);
            line();
        }

        public void mark(String input) {
            try {
                int index = Integer.parseInt(input);
                done.set(index - 1, "X");
                line();
                System.out.println("Marked item " + index + " as done.");
                line();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                line();
                System.out.println("Invalid input for 'unmark' command.");
                line();
            }
        }

        public void unmark(String input) {
            try {
                int index = Integer.parseInt(input);
                done.set(index - 1, " ");
                line();
                System.out.println("Unmarked item " + index + " as done.");
                line();
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                line();
                System.out.println("Invalid input for 'unmark' command.");
                line();
            }
        }

        public void print() {
            line();
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + "[" + done.get(i) + "] " + list.get(i) + "\n");
            }
            line();
        }
    }
}
