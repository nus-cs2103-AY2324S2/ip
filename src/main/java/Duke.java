import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Riri";
//        String logo = "  ____        _        \n"
//                + " |  _ \\ _   _| | _____ \n"
//                + " | | | | | | | |/ / _ \\\n"
//                + " | |_| | |_| |   <  __/\n"
//                + " |____/ \\__,_|_|\\_\\___|\n";
//        System.out.println(logo);
        System.out.println("-------------------------");
        System.out.println("What's up " + name);
        System.out.println("What can't I do for you?");
        System.out.println("-------------------------");

        Scanner sc = new Scanner(System.in);

        Mylist mylist = new Mylist();

        boolean on = true;
        while (on) {
            String command = sc.nextLine();
            if (command.matches("bye")) {
                on = false;
                break;
            }
            else if (command.matches("list")) {
                mylist.returnList();
                continue;
            }
            else if (command.matches("\\bmark\\b.*")) {
                String[] words = command.split("\\s+");
                mylist.mark(Integer.parseInt(words[1]));
                continue;
            }
            else if (command.matches("\\bunmark\\b.*")) {
                String[] words = command.split("\\s+");
                mylist.unmark(Integer.parseInt(words[1]));
                continue;
            }
            else if (command.matches("\\bdeadline\\b.*")) {
                String[] words = command.split("/by");
                mylist.addTodo(new Deadline(words[0].trim(), words[1].trim()));
                System.out.println("Added deadline");
                continue;
            }
            else if (command.matches("\\bevent\\b.*")) {
                String[] words = command.split("/from+");
                String[] from = words[1].split("/to");
                mylist.addTodo(new Event(words[0].trim(), from[0].trim(),from[1].trim()));
                System.out.println("Added event.");
                continue;
            }
            else if (command.matches("\\btodo\\b.*")) {
                String[] words = command.split("todo");
                mylist.addTodo(new Todo(words[1].trim()));
                System.out.println("Added todo.");
                continue;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------");
    }
}