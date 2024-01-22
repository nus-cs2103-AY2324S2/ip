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

        while (scanner.hasNextLine()) {

            String input = getUserInput().trim();
            String command = input.split(" ")[0];

            try {
                switch (command) {
                    case terminatePhrase:
                        terminate();
                        System.exit(0);
                        break;
                    case listCommand:
                        this.printList(false);
                        continue;
                    case markCommand:
                    case unmarkCommand:
                        HandleTaskMarking(input);
                        break;
                    case todoCommand:
                    case deadlineCommand:
                    case eventCommand:
                        HandleTaskCreation(input);
                        break;
                    default:
                        throw new BobException.InvalidCommand("Sorry, I'm not sure what command that is.");
                }
            } catch (BobException.InvalidCommand e) {
                System.out.println(e.getMessage());
            } catch (BobException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void HandleTaskMarking(String input) throws BobException {
        if ((input.contains(this.markCommand) || input.contains(this.unmarkCommand))) {

            String[] args = input.split(" ");
            if (args.length < 2) throw new BobException("The command " + args[0] + " requires a task ID.");

            this.printLine();
            int taskId = Integer.parseInt(args[1]) - 1;
            String userCommand = args[0];

            if (userCommand.equals(this.markCommand)) {
                this.markDone(taskId);
                System.out.println("    You have marked task as done:");
            } else {
                this.markUndone(taskId);
                System.out.println("    You have marked task as undone:");
            }

            Task task = this.list.get(taskId);
            System.out.println("    " + task.getStatus() + " " + task);
            this.printLine();
        }
    }

    private void HandleTaskCreation(String input) throws BobException {

        Task t = null;

        if (input.contains(this.todoCommand)) {

            if (input.length() == this.todoCommand.length()) throw new BobException("The command " + this.todoCommand + " requires a task description.");

            String description = input.substring(this.todoCommand.length() + 1);

            t = addItem(new Task(description));
        }

        if (input.contains(this.deadlineCommand)) {

            if (input.length() == this.deadlineCommand.length()) throw new BobException("The command " + this.deadlineCommand + " requires both a task description and a deadline.");
            input = input.substring(this.deadlineCommand.length() + 1);

            String[] split = input.split("/");
            if (split.length < 2) throw new BobException("The command " + this.deadlineCommand + " requires both a task description and a deadline.");

            t = addItem(new Deadline(split[0].substring(0, split[0].length() - 1), split[1].substring(3)));
        }

        if (input.contains(this.eventCommand)) {

            if (input.length() == this.eventCommand.length()) throw new BobException("The command " + this.eventCommand + " requires a task description, a start date, and an end date.");
            input = input.substring(this.eventCommand.length() + 1);

            String[] split = input.split("/");
            if (split.length < 3) throw new BobException("The command " + this.eventCommand + " requires a task description, a start date, and an end date.");

            t = addItem(new Event(split[0].substring(0, split[0].length() - 1), split[1].substring(5), split[2].substring(3)));
        }

        if (t != null) PrintTaskAddMessage(t);
    }

    private void PrintTaskAddMessage(Task t) {
        this.printLine();
        System.out.println("    Here is your newly added task:");
        System.out.println("        " + t.getType() + t.getStatus() + " " + t);
        this.printList(true);
        this.printLine();
    }

    public static void main(String[] args) {
        Bob bob = new Bob();
        bob.start();
    }
}
