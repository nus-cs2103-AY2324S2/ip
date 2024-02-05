import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    // A class for elements in the itemList, with a name and a indicator of whether the item is marked
    private static class listItem{
        private final String listItemName;
        private boolean isDone;

        // Constructor for the listItem class, initialised with the name of the item
        public listItem(String itemName) {
            this.listItemName = itemName;
            this.isDone = false;
        }

        // Allows the user to modify the status of the item
        public void modifyStatus(boolean status) {
            this.isDone = status;
        }

        // Returns the name of the item
        public String getName() {
            return this.listItemName;
        }

        // Returns the status of the item
        public boolean getStatus() {
            return this.isDone;
        }
    }

    // ArrayList for storing user-added items.
    private static ArrayList<listItem> itemList = new ArrayList<>();

    // Greets the user
    private static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Greetings! I'm Barry.\n" + "\t How could I assist you?");
        System.out.println("\t____________________________________________________________\n");
    }

    // Exit the program
    private static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye, see you next time! :)");
        System.out.println("\t____________________________________________________________\n");
    }

    // Echo mode, echos all input back to the user unless the input is "bye"
    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // String input
            String inputText = scanner.nextLine().strip();
            if (inputText.equals("bye")) {
                break;
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t " + inputText);
                System.out.println("\t____________________________________________________________\n");
            }
        }
    }

    // List mode, allows user to enter items into a list, and print all items in the list currently with "list", exits with "bye"
    private static void listMode() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // String input
            String inputText = scanner.nextLine().strip();
            if (inputText.equals("bye")) {
                // Exit list mode
                break;
            } else if (inputText.equals("list")) {
                // List all items in itemList currently
                int itemListSize = itemList.size();
                System.out.println("\t____________________________________________________________");
                for (int i = 0; i < itemListSize; i++) {
                    System.out.println("\t " + (i + 1) + ". " + itemList.get(i).getName());
                }
                System.out.println("\t____________________________________________________________");
            } else {
                // Add new item to itemList
                itemList.add(new listItem(inputText));
                System.out.println("\t____________________________________________________________");
                System.out.println("\t added: " + inputText);
                System.out.println("\t____________________________________________________________\n");
            }
        }
    }

    public static void main(String[] args) {
        greet();
        listMode();
        exit();
    }
}
