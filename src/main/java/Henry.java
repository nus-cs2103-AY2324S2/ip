import java.util.Scanner;
public class Henry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "  _    _                       \n" +
                " | |  | |                      \n" +
                " | |__| | ___ _ __  _ __ _   _ \n" +
                " |  __  |/ _ \\ '_ \\| '__| | | |\n" +
                " | |  | |  __/ | | | |  | |_| |\n" +
                " |_|  |_|\\___|_| |_|_|   \\__, |\n" +
                "                          __/ |\n" +
                "                         |___/ \n";
        String greetMessage = "Hello! I'm Henry\nWhat can I do for you?";
        String exitMessage = "See you again bro!";

        System.out.println(logo);
        System.out.println(greetMessage);
        System.out.println();

        while (true) {
            String currentMessage = scanner.nextLine();
            if (currentMessage.equals("bye")) {
                System.out.println(exitMessage);
                break;
            } else {
                System.out.println(currentMessage);
                System.out.println();
            }
        }
    }
}