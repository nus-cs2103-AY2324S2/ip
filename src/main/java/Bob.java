import java.util.*;

public class Bob {

    private ArrayList<Task> storage;
    private Scanner scanner;
    private final String terminatePhrase = "bye";
    private final String listCommand = "list";
    private final String markCommand = "mark";
    private final String unmarkCommand = "unmark";

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
        this.storage.add(new Task(item));
    }

    /**
     * Mark item done.
     */
    private void markDone(int item) {
        this.storage.get(item).updateStatus(true);
    }

    /**
     * Mark item undone.
     */
    private void markUndone(int item) {
        this.storage.get(item).updateStatus(false);
    }

    /**
     * List items in storage.
     */
    private void viewStorage() {

        this.printLine();

        for (int i = 0; i < this.storage.size(); i++) {
            Task task = this.storage.get(i);
            System.out.println("    " + (i + 1) + "." + task.getStatus() + " " + task);
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

            if (input.contains(" ") && (input.contains(this.markCommand) || input.contains(this.unmarkCommand))) {

                String[] args = input.split(" ");

                this.printLine();
                int taskId = Integer.parseInt(args[1]) - 1;
                String command = args[0];

                if (command.equals(this.markCommand)) {
                    this.markDone(taskId);
                    System.out.println("    You have marked task as done:");
                } else {
                    this.markUndone(taskId);
                    System.out.println("    You have marked task as undone:");
                }

                Task task = this.storage.get(taskId);
                System.out.println("    " + task.getStatus() + " " + task);
                this.printLine();
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
