import java.util.Scanner;

public class Duke {

    private static final String longLine = "____________________________________________________________";

    public static void main(String[] args) {

        // Input reader
        Scanner inputReader = new Scanner(System.in);

        // Print logo
        String logo = " __   ___  _____   __       __       __    __      ___ \n"
                + "|  | /  / |_   _| |  |     |  |     |  |  |  |    / _ \\ \n"
                + "|  |/  /    | |   |  |     |  |     |  |  |  |   / /_\\ \\ \n"
                + "|  |\\  \\   _| |_  |  |___  |  |___  |  |__|  |  / _____ \\ \n"
                + "|__| \\__\\ |_____| |______| |______|  \\______/  /_/     \\_\\ \n";
        System.out.println(logo);

        // Greet
        System.out.println(Duke.longLine);
        System.out.println("Killua online. What's my next quest?");
        System.out.println(Duke.longLine);

        // Echo
        while (true) {
            String task = inputReader.nextLine();
            if (task.equals("bye")) {
                break;
            } else {
                System.out.println(Duke.longLine);
                System.out.println(task);
                System.out.println(Duke.longLine);
            }
        }

        // Exit
        System.out.println(Duke.longLine);
        System.out.println("Alright, I'm always one call away.");
        System.out.println(Duke.longLine);


    }
}
