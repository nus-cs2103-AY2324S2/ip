import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String name = "Big Boy";
        String greeting = "Hello! I'm " + name + ", your new friend!" + "\n" + "What can I do for you?";
        String line = "____________________________________________________________";
        String exit = "Bye. Hope to see you again soon!";

        // add input. program repeats user's input until user types "bye"
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            System.out.println(line);
            System.out.println("\t" + input);
            System.out.println(line);
            input = in.nextLine();
        }
        
        in.close();
        System.out.println(line);
        System.out.println(exit);
        System.out.println(line);
    }
}
