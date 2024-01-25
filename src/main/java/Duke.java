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
        
        // if user types "bye", program exits
        while (!input.equals("bye")) {
            System.out.println(line);

            // if user types "list", program prints out all the items in the list
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (String item : stringList) {
                    System.out.println(item);
                }
                System.out.println(line);
                input = in.nextLine();
                continue;
                
            // if user types anything else, program adds the text to the list
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
