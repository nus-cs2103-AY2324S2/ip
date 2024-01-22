import java.util.*;

public class Bob {

    private ArrayList<Task> list;
    private Scanner scanner;

    private final String terminatePhrase = "bye";

    private final String listCommand = "list";
    private final String markCommand = "mark";
    private final String unmarkCommand = "unmark";


    private final String todoCommand = "todo";
    private final String deadlineCommand = "deadline";
    private final String eventCommand = "event";

    public Bob() {
        this.scanner = new Scanner(System.in);
        list = new ArrayList<>();
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
    private Task addItem(Task t) {
        this.list.add(t);
        return t;
    }

    /**
     * Mark item done.
     */
    private void markDone(int item) {
        this.list.get(item).updateStatus(true);
    }

    /**
     * Mark item undone.
     */
    private void markUndone(int item) {
        this.list.get(item).updateStatus(false);
    }

    /**
     * List items in list.
     */
    private void printList(boolean summarized) {

        if (!summarized) {
            this.printLine();
            for (int i = 0; i < this.list.size(); i++) {
                Task task = this.list.get(i);
                System.out.println("    " + (i + 1) + "." + task.getType() + task.getStatus() + " " + task);
            }
            this.printLine();
        } else {
            System.out.println("    You have " + this.list.size() + " tasks in your list.");
        }
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
                this.printList(false);
                continue;
            }

            if ((input.contains(this.markCommand) || input.contains(this.unmarkCommand))) {

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

                Task task = this.list.get(taskId);
                System.out.println("    " + task.getStatus() + " " + task);
                this.printLine();
                continue;
            }

            if (input.contains(this.todoCommand) || input.contains(this.deadlineCommand) || input.contains(this.eventCommand)) {

                Task t = null;

                if (input.contains(this.todoCommand)) {

                    t = addItem(new Task(input.substring(this.todoCommand.length() + 1)));
                }

                if (input.contains(this.deadlineCommand)) {

                    input = input.substring(this.deadlineCommand.length() + 1);

                    String[] split = input.split("/");
                    t = addItem(new Deadline(split[0].substring(0, split[0].length() - 1), split[1].substring(3)));
                }

                if (input.contains(this.eventCommand)) {

                    input = input.substring(this.eventCommand.length() + 1);

                    String[] split = input.split("/");
                    t = addItem(new Event(split[0].substring(0, split[0].length() - 1), split[1].substring(5), split[2].substring(3)));
                }

                this.printLine();
                System.out.println("    added: " + input);
                System.out.println("        " + t.getType() + t.getStatus() + " " + t);
                this.printList(true);
                this.printLine();

            }
        }

        terminate();
    }

    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.start();
    }
}
