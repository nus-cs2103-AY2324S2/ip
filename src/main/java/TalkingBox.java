import java.util.Scanner;
import java.util.ArrayList;

public class TalkingBox {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String NAME = "Talking Box";
        ArrayList<String> taskList = new ArrayList<>();

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_s|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        System.out.println("Hello, I am the " + NAME);
        System.out.println("What do you want to talk about?");

        String input = in.nextLine();
        while (!(input.equals("bye"))) {
            if (!(input.equals("list"))) {
                taskList.add(input);
                System.out.println("added: " + input);
            } else {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ": " + taskList.get(i));
                }
            }
            input = in.nextLine();
        }

        System.out.println("Goodbye!");

    }
}

