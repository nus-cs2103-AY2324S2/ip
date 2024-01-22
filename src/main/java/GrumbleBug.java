import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.Scanner;

public class GrumbleBug {

    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int u = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getFullStatus());
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
                + "To leave, reply with 'byebye'.\n";
        System.out.println(starter);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();

        while (true) {
            String input1 = sc.nextLine();
            String words[] = input1.split(" ");
            String firstWord = words[0];
            String secondWord = words.length > 1 ? words[1] : null;
            if (input1.equals("byebye")) {
                System.exit(0);
            } else if (input1.equals("list")) { // show the list!
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "You have these things in your list...";
                System.out.println(reply);
                GrumbleBug.printList(myList);
            } else if (firstWord.equals("mark") && isInt(secondWord)) {
                int u = Integer.parseInt(secondWord);
                myList.get(u-1).setDone(true);
                System.out.println("Ok, marked it.");
            } else if (firstWord.equals("unmark") && isInt(secondWord)) {
                int u = Integer.parseInt(secondWord);
                myList.get(u-1).setDone(false);
                System.out.println("Ugh, unmarked it.");
            } else { // add to list
                Task task = new Task(input1, myList.size() + 1);
                myList.add(task);
                String reply = "GrumbleBug:"
                    + "_______________________________________\n"
                    + "Fine, added your stupid " + input1 + "."
                    + "\n_______________________________________\n";
                System.out.println(reply);
            }
        }
    }
}
