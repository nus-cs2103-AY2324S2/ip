import java.util.Scanner;
public class Groot {
    private static final String exitCommand = "bye";
    public static void main(String[] args) {
        logo();
        greet();

        Scanner scanner = new Scanner(System.in);

        for (;;) {
           String message = scanner.nextLine();
           if (message.equals(exitCommand)) {
               farewell();
               break;
           }
           echo(message);
        }
    }

    private static void logo() {
        String logo =
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣴⣾⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⠃⣽⡅⢲⡎⢩⣯⠑⡄⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⠢⣉⣥⣦⣤⣬⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⠰⢿⣿⣿⣿⡿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀\n" +
                "⠀⢀⣾⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⡄⠀\n" +
                "⠀⢸⣿⢿⣿⣿⣿⣿⣿⣶⣛⡛⠛⠒⠒⠒⠒⠀⠀⠀⠀⢀⣶⣿⣷⠁⠀\n" +
                "⠀⣿⡇⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣾⡇⠀⢀⣼⣿⠟⠸⠄⠀\n" +
                "⠀⡟⠁⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀⠀\n" +
                "⠀⡇⠀⠀⠀⠀⠈⣿⠛⠻⠿⢿⣿⣿⣿⣿⣿⠿⢿⣿⠁⠀⠀⠀⠀⢸⠀\n" +
                "⠀⡇⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀⠀⢸⠀\n" +
                "⠀⡇⠀⠀⠀⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠈⢆\n" +
                "⢰⡇⠰⡀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⡆⢸\n" +
                "⠘⢣⠀⠁⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⢠⠟\n" +
                "⠀⠁⠓⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠃⠀⠀⠀⠀⠀⠁⠀\n" +
                "⠀⠀⠀⠀⣀⠇⢀⣷⢀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⠐⠆⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠁⠀⠀⠀";
        System.out.println(logo);
    }

    private static void greet() {
        String greeting =
                "=================================\n" +
                " Hello! I'm Mike WAZOWSKI.\n" +
                " What can I do for you?\n" +
                "=================================\n";
        System.out.println(greeting);
    }

    private static void farewell() {
        String farewell =
                "=================================\n" +
                " Where are you going? We'll talk.\n" +
                " We'll have a latte.\n" +
                "=================================\n";
        System.out.println(farewell);
    }

    private static void echo(String message) {
        String reply =
                "=================================\n" +
                message + "\n" +
                "=================================\n";
        System.out.println(reply);
    }
}
