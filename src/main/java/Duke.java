import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = 
                        " _____   _____  _    _ \n"
                        + "|  __ \\ / ____|| |  | |\n"
                        + "| |__) | (___  | |__| |\n"
                        + "|  _  / \\___ \\ |  __  |\n"
                        + "| | \\ \\ ____) || |  | |\n"
                        + "|_|  \\_|_____/ |_|  |_|\n";
        System.out.println(layer("Hello! I'm \n" + logo));
        while (true) {
            String input = sc.nextLine(); // Read user input

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(layer("Bye. Hope to see you again soon!"));
                break; // Break the loop to end the program
            }

            System.out.println(layer(input)); // Echo the input
        }

        sc.close(); // Close the scanner
    }

    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }
}
