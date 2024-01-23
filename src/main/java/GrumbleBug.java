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
                + "I'm GrumbleBug.\n";
        System.out.println(starter);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<>();

        while (true) {
            String input1 = sc.nextLine();
//            String words[] = input1.split(" ");
//            String firstWord = words[0];
//            String secondWord = words.length > 1 ? words[1] : null;
            if (input1.equals("byebye")) {
                System.exit(0);
            } else if (input1.equals("list")) { // show the list!
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "You have these things in your list...";
                System.out.println(reply);
                GrumbleBug.printList(myList);
            } else if (input1.equals("mark")) {
                System.out.println("Which task to mark? Give me the number");
                try {
                    int u = Integer.parseInt(sc.nextLine());
                    myList.get(u-1).setDone(true);
                    System.out.println("Ok, marked it.");
                } catch (Exception e) {
                    System.err.println("That wasn't a good number");
                }
            } else if (input1.equals("unmark")) {
                try {
                    int u = Integer.parseInt(sc.nextLine());
                    myList.get(u-1).setDone(true);
                    System.out.println("Ugh, unmarked it.");
                } catch (Exception e) {
                    System.err.println("That wasn't a good number");
                }
            } else if (input1.equals("todo")) { // add to list
                System.out.println("Task name?");
                String name = sc.nextLine();
                Task task = new Task(name, myList.size() + 1);
                myList.add(task);
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "Fine, added your stupid " + input1 + "."
                        + "\n";
                System.out.println(reply);
            } else if (input1.equals("deadline")) { // add to list
                System.out.println("Task name?");
                String name = sc.nextLine();
                System.out.println("Wow ok.. due by?");
                String endDate = sc.nextLine();
                Task task = new Task(name, myList.size() + 1, endDate);
                myList.add(task);
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "K, better finish " + input1 + " on time."
                        + "\n_______________________________________\n";
                System.out.println(reply);
            } else if (input1.equals("event")) { // add to list
                System.out.println("Task name?");
                String name = sc.nextLine();
                System.out.println("Start date?");
                String startDate = sc.nextLine();
                System.out.println("End date?");
                String endDate = sc.nextLine();
                Task task = new Task(name, myList.size() + 1, startDate, endDate);
                myList.add(task);
                String reply = "GrumbleBug:"
                        + "_______________________________________\n"
                        + "Better not miss your silly " + input1 + " event."
                        + "\n_______________________________________\n";
                System.out.println(reply);
            } else {
                // error, cannot understand
                System.out.println("I don't understand. Try again, or go away");
            }
        }
    }
}
