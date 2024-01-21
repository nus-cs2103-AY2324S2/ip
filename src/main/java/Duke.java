import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String greeting = "_______________________________________________________\n" +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";

        System.out.println(greeting);

        List<String> todo_list = new ArrayList<>();

        while(true) {
            Scanner sc = new Scanner(System.in);

            String todo = sc.nextLine();
            if (todo.equals("bye")) break;
            else if (todo.equals("list")) {
                System.out.println("_______________________________________________________\n");
                for (int i = 0; i < todo_list.size(); i++) {
                    System.out.println(i + 1 + ": " + todo_list.get(i));
                }
                System.out.println("_______________________________________________________\n");
            }
            else {
                todo_list.add(todo);
                System.out.println("_______________________________________________________\n added: " +
                        todo + "\n" +
                        "_______________________________________________________\n");
            }
        }

        String exit = "_______________________________________________________\n" +
                "Bye. Hope to see you soon!\n" +
                "_______________________________________________________\n";
        System.out.println(exit);
    }
}
