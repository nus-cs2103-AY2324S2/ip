import java.util.*;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?" );
        System.out.println("-------------------------------");

        while (true) {
            String text1 = scan.nextLine();
            int indentation = text1.length() + 1;
            String spaces = " ".repeat(indentation);

            if (text1.equals("bye")) {
                System.out.println("-------------------------------");
                System.out.println(spaces + "Bye. Hope to see you again soon!");
                System.out.println("-------------------------------");
                break;
            }

            else if (text1.equals("list")) {
                System.out.println("-------------------------------");
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println(spaces + (i + 1) + ". " + arr.get(i));
                }
                System.out.println("-------------------------------");
            }

            else {
                System.out.println("-------------------------------");
                System.out.println(spaces + "added: " + text1);
                arr.add(text1);
                System.out.println("-------------------------------");
            }
        }
    }
}
