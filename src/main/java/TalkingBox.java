import java.util.Scanner;

public class TalkingBox {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String NAME = "Talking Box";
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
            System.out.println(input);
            input = in.nextLine();
        }

        System.out.println("Goodbye!");

    }
}

