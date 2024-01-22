import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Scanner;

public class GrumbleBug {

    public static void printList(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
    public static void main(String[] args) {
        String starter = "GrumbleBug:"
                + "_______________________________________\n"
                + "Oh hey, adventurer.\n"
                + "I'm GrumbleBug.\n"
                + "You wanna list me some things?\n"
                + "_______________________________________\n"
                + "\n"
                + "Reply to add things to your list.\n"
                + "To see your list, type 'list'.\n"
                + "To leave, reply with 'bye'.\n";
        System.out.println(starter);
        Scanner sc = new Scanner(System.in);
        ArrayList<String> myList = new ArrayList<>();

        while (true) {
            String input1 = sc.nextLine();
            if (input1.equals("bye")) {
                System.exit(0);
            } else if (input1.equals("list")) { // show the list!
                String reply = "GrumbleBug:"
                    + "_______________________________________\n"
                    + "You have these things in your list...";
                System.out.println(reply);
                GrumbleBug.printList(myList);
            } else { // add to list
                myList.add(input1);
                String reply = "GrumbleBug:"
                    + "_______________________________________\n"
                    + "Fine, added your stupid " + input1 + "."
                    + "\n_______________________________________\n";
                System.out.println(reply);
            }
        }
    }
}
