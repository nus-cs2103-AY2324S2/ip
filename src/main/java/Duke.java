import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Riri";
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------");
        System.out.println("What's up " + name);
        System.out.println("What can't I do for you?");
        System.out.println("-------------------------");

        Scanner sc = new Scanner(System.in);

        Todo todo = new Todo();

        boolean on = true;
        while (on) {
            String command = sc.nextLine();
            if (command.matches("bye")) {
                on = false;
                break;
            }
            else if (command.matches("list")) {
                todo.returnList();
                continue;
            }
            else if (command.matches("\\bmark\\b.*")) {
                String[] words = command.split("\\s+");
                todo.mark(Integer.parseInt(words[1]));
                continue;
            }
            else if (command.matches("\\bunmark\\b.*")) {
                String[] words = command.split("\\s+");
                todo.unmark(Integer.parseInt(words[1]));
                continue;
            }
            System.out.println("added: " + command);
            todo.addTodo(new Task(command));
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------");
    }
}