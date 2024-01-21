import java.util.*;

public class Bob {

    private ArrayList<String> storage;
    private Scanner scanner;
    private final String terminatePhrase = "bye";
    private final String listCommand = "list";

    public Bob() {
        this.scanner = new Scanner(System.in);
        storage = new ArrayList<>();
    }

    /**
     * Method for getting user input.
     */
    private String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Utility function to print dividers.
     */
    private void printLine() {
        System.out.println("    +----------------------------------------------------------+");
    }

    /**
     * Method for greeting the user.
     */
    private void greet() {
        this.printLine();
        System.out.println("    Hello! I'm Bob, a personal assistant." );
        System.out.println("    How can I help you?");
        this.printLine();
    }

    /**
     * Method for ending the conversation.
     */
    private void terminate() {
        this.printLine();
        System.out.println("    Until next time! Goodbye!");
        this.printLine();
    }

    /**
     * Add items to storage.
     */
    private void addItem(String item) {
        this.storage.add(item);
    }

    /**
     * List items in storage.
     */
    private void viewStorage() {

        this.printLine();

        for (int i = 0; i < this.storage.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + this.storage.get(i));
        }

        this.printLine();
    }

    /**
     * Main logic of the chatbot.
     */
    public void start() {

        this.greet();

        while (true) {
            String input = getUserInput();

            if (input.equals(this.terminatePhrase)) break;
            if (input.equals(this.listCommand)) {
                this.viewStorage();
                continue;
            }

            addItem(input);

            this.printLine();
            System.out.println("    added: " + input);
            this.printLine();
        }

        terminate();
    }

    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.start();
    }
}
