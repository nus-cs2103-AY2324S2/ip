import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String input;
        String line = "_________________________________";
        ArrayList<String> storage = new ArrayList<String>();
        System.out.println(line);
        System.out.println("Hello! I'm ChatterPal!");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                for (int j=0; j<storage.size(); j++) {
                    System.out.printf("%d. %s\n", j+1, storage.get(j));
                }
                System.out.println(line);

            } else if (input.equals("blah")) {
                System.out.println("blah");
            } else {
                storage.add(input);
                System.out.println(line);
                System.out.printf("added: %s\n", input);
                System.out.println(line);
            }
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Farewell! Can't wait to catch up with you again. Until next time, " +
                "take care and stay awesome! ");
        System.out.println(line);
    }
}
