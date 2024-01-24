import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        String name = "Big Boy";
        String greeting = "Hello! I'm " + name + ", your new friend!" + "\n" + "What can I do for you?";
        String line = "____________________________________________________________";
        String exit = "Bye. Hope to see you again soon!";
        ArrayList<String> stringList = new ArrayList<>();
        int itemIndex = 1;


        // add input. program repeats user's input until user types "bye"
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            System.out.println(line);

            if (input.equals("list")) {
                for (String item : stringList) {
                    System.out.println(item);
                }
                System.out.println(line);
                input = in.nextLine();
                continue;
            } else {
                System.out.println("added: " + input);
                stringList.add(itemIndex + ". " + input);
                itemIndex++;
            }

            System.out.println(line);
            input = in.nextLine();
        }

        in.close();
        System.out.println(line);
        System.out.println(exit);
        System.out.println(line);
    }
}
