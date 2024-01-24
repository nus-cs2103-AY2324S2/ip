import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Homie");
        System.out.println("What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();  // Read user command
        ArrayList<String> myList = new ArrayList<String>(); // Create an ArrayList object

        int index = 1;

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(line);
                for (String s : myList) {
                    System.out.println(index + ". " + s);
                    index++;
                }
                System.out.println(line + "\n");
                index = 1; // Reset index to 1
                command = scanner.nextLine(); // Read next command
                continue;
            }
            myList.add(command); // add command to list
            System.out.println(line + "\nadded: " + command + "\n" + line);  // Echo added
            command = scanner.nextLine(); // Read next command
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
