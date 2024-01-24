import java.util.ArrayList;
import java.util.*;
public class Lunaris {
    public static void main(String[] args) {
        String name = "Lunaris";
        String indentation = "  ";
        String indentedLine = "  _________________________________________________________";
        // Just for convenience of copy paste.
        // System.out.println(indentedLine);

        // Print out greeting message
        System.out.println(indentedLine);
        System.out.println(indentation + "Hey! I'm " + name + "\n"
            + "Is there anything I can do for you?");
        System.out.println(indentedLine);

        ArrayList<String> inputList = new ArrayList<>();

        /*
        Main body of addList task.
        Handle case where input is "bye" or "list".
        Add input to arrayList otherwise.
        */
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(indentedLine);
                System.out.println(indentation +
                        "Leaving so soon? Alright, have a great day ahead!");
                System.out.println(indentedLine);
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                System.out.println(indentedLine);
                for (int i = 0; i < inputList.size(); i++){
                    System.out.println((i + 1) + ". " + inputList.get(i));
                }
            }
            else {
                inputList.add(input);
                System.out.println(indentedLine);
                System.out.println(indentation + "added: " + input);
                System.out.println(indentedLine);
            }
        }
    }
}
