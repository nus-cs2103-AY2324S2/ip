import java.util.ArrayList;
import java.util.Scanner;

public class BobUI {

    private Scanner scanner;

    public BobUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean acceptingInput() {
        return this.scanner.hasNextLine();
    }

    /**
     * Method for getting user input.
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Utility function to print dividers.
     */
    public void printLine() {
        System.out.println("    +----------------------------------------------------------+");
    }

    /**
     * Method for greeting the user.
     */
    public void greet() {
        this.printLine();
        System.out.println("    Hello! I'm Bob, a personal assistant.");
        System.out.println("    How can I help you?");
        this.printLine();
    }

    /**
     * Method for ending the conversation.
     */
    public void terminate() {
        this.printLine();
        System.out.println("    Until next time! Goodbye!");
        this.printLine();
    }

    /**
     * List items in list.
     */
    public void printList(boolean summarized, ArrayList<Task> list) {

        if (!summarized) {

            this.printLine();

            System.out.println("    Here are the tasks in your list:");

            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println("    " + (i + 1) + "." + task.getType()
                        + task.getStatus() + " " + task);
            }
            this.printLine();
        } else {
            System.out.println("    You have " + list.size()
                    + " tasks in your list.");
        }
    }

    public void printTaskAddMessage(Task t, ArrayList<Task> list) {
        this.printLine();
        System.out.println("    Here is your newly added task:");
        System.out.println("        " + t.getType() + t.getStatus() + " " + t);
        this.printList(true, list);
        this.printLine();
    }

}
