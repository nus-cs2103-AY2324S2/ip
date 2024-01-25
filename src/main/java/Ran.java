import java.util.Scanner;

public class Ran {
    public static void main(String[] args) {
        System.out.println("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        System.out.println(art);
        System.out.println("What would you like to do today?");
        boolean running = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("____________________________________________________________");
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    running = false;
                    break;
                default:
                    System.out.println(command+"?");
            }
        } while (running);

        System.out.println("Goodbye, please return soon.");
    }
}
