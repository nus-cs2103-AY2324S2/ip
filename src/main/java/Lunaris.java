import java.util.*;
public class Lunaris {
    public static void main(String[] args) {
        String name = "Lunaris";
        String indentation = "  ";
        String indentedLine = "  _________________________________________________________";
        // Just for convenience of copy paste.
        // System.out.println(indentedLine)

        // Print out greeting message
        System.out.println(indentedLine);
        System.out.println(indentation + "Hey! I'm " + name + "\n"
            + "Is there anything I can do for you?");
        System.out.println(indentedLine);

        // Scan for Input and echo it.
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("bye")) {
                System.out.println(indentedLine);
                System.out.println(indentation + input);
                System.out.println(indentedLine);
            }
            else {
                break;
            }
        }

        // Print out goodbye message
        System.out.println(indentedLine);
        System.out.println("Leaving so soon? Alright, have a great day ahead!");
        System.out.println(indentedLine);
    }
}
