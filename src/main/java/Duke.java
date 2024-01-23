import java.io.*;
import java.util.*;
public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");

        String input = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println( "--------------------------------");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }
                System.out.println( "-------------------------------- \n");
            }
            else {
                list.add(input);
                System.out.println( "-------------------------------- \n" +
                                    "added: " + input + "\n" +
                                    "-------------------------------- \n");
            }
            input = sc.nextLine();
        }

        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }
}
