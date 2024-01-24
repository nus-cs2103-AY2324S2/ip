import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;


public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Taro\n"
                + "What can I do for you?\n";

        System.out.println(greeting);

        Scanner s = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = s.nextLine();
            String[] words = input.split("\\s+");
            String command = words[0];

            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                int num = 1;
                Iterator<Task> it = list.iterator();
                System.out.println("Here are the tasks in the list:");
                while (it.hasNext()) {
                    System.out.println(num + "." + it.next().toString());
                    num++;
                }

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(words[1]);
                list.get(index-1).markAsDone();

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(words[1]);
                list.get(index-1).markAsUndone();

            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);

            }
        }
        s.close();
        System.out.println("BYE BYE");
    }
}
