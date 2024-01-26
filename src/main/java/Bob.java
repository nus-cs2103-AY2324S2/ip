import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    private static final String TERMINATE_COMMAND = "bye";

    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";

    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";


    private ArrayList<Task> list;
    private BobUI ui;
    private BobStorage storage;

    public Bob(BobUI ui, BobStorage storage) {
        this.list = new ArrayList<>();
        this.ui = ui;
        this.storage = storage;
    }

    private void deleteTask(int taskId) {
        this.list.remove(taskId);
        this.storage.updateTaskList(this.list);
    }

    /**
     * Mark item done.
     */
    private void markDone(int item) {
        this.list.get(item).updateStatus(true);
        this.storage.updateTaskList(this.list);
    }

    /**
     * Mark item undone.
     */
    private void markUndone(int item) {
        this.list.get(item).updateStatus(false);
        this.storage.updateTaskList(this.list);
    }

    /**
     * Main logic of the chatbot.
     */
    public void start() {

        try {
            this.list = this.storage.loadSavedTasks();
        } catch (BobException.FileAccessError e) {
            System.out.println(e.getMessage());
        } catch (BobException.CorruptedSaveData e) {
            System.out.println(e.getMessage());
        }

        this.ui.greet();

        while (this.ui.acceptingInput()) {

            String input = this.ui.getUserInput().trim();
            final String command = input.split("\\s+")[0];

            try {
                switch (command) {
                case Bob.TERMINATE_COMMAND:
                    this.ui.terminate();
                    System.exit(0);
                    break;
                case Bob.LIST_COMMAND:
                    this.ui.printList(false, this.list);
                    continue;
                case Bob.MARK_COMMAND:
                case Bob.UNMARK_COMMAND:
                    this.handleTaskMarking(input);
                    break;
                case Bob.TODO_COMMAND:
                case Bob.DEADLINE_COMMAND:
                case Bob.EVENT_COMMAND:
                    this.handleTaskCreation(input);
                    break;
                case Bob.DELETE_COMMAND:
                    this.handleTaskDeletion(input);
                    break;
                default:
                    throw new BobException.InvalidCommand("Sorry, I'm not sure what command that is.");
                }
            } catch (BobException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleTaskMarking(String input) throws BobException {

        String[] args = input.split("\\s+");
        if (args.length < 2) {
            throw new BobException("The command " + args[0] + " requires a task ID.");
        }

        this.ui.printLine();
        int taskId = Integer.parseInt(args[1]) - 1;

        if (!(taskId < this.list.size()) || taskId < 0) {
            throw new BobException("The command " + args[0] + " requires a valid ID.");
        }

        String userCommand = args[0];

        if (userCommand.equals(Bob.MARK_COMMAND)) {
            this.markDone(taskId);
            System.out.println("    You have marked task as done:");
        } else {
            this.markUndone(taskId);
            System.out.println("    You have marked task as undone:");
        }

        Task task = this.list.get(taskId);
        System.out.println("    " + task.getType() + task.getStatus() + " " + task);
        this.ui.printLine();
    }

    private void handleTaskCreation(String input) throws BobException {

        Task t = null;

        try {
            if (input.contains(Bob.TODO_COMMAND)) {

                if (input.length() == Bob.TODO_COMMAND.length()) {
                    throw new BobException("The command " + Bob.TODO_COMMAND + " requires a task description.");
                }

                String description = input.substring(Bob.TODO_COMMAND.length() + 1);

                t = this.storage.addItem(new Task(description), this.list);
            }

            if (input.contains(Bob.DEADLINE_COMMAND)) {

                if (!input.contains("/by")) {
                    throw new BobException("The command " + Bob.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                if (input.length() == Bob.DEADLINE_COMMAND.length()) {
                    throw new BobException("The command " + Bob.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                input = input.substring(Bob.DEADLINE_COMMAND.length() + 1);

                String[] split = input.split("/by");
                if (split.length < 2) {
                    throw new BobException("The command " + Bob.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                t = this.storage.addItem(new Deadline(split[0].substring(0, split[0].length() - 1),
                        split[1].substring(1)), this.list);
            }

            if (input.contains(Bob.EVENT_COMMAND)) {

                if (!input.contains("/from") && !input.contains("/to")) {
                    throw new BobException("The command " + Bob.EVENT_COMMAND
                            + " requires a task description, a start date, and an end date.");
                }

                if (input.length() == Bob.EVENT_COMMAND.length()) {
                    throw new BobException("The command " + Bob.EVENT_COMMAND
                            + " requires a task description, a start date, and an end date.");
                }

                input = input.substring(Bob.EVENT_COMMAND.length() + 1);

                String[] split = input.split("/");

                if (split.length < 3) {
                    throw new BobException("The command " + Bob.EVENT_COMMAND
                            + " requires a task description, a start date, and an end date.");
                }

                t = this.storage.addItem(new Event(split[0].substring(0, split[0].length() - 1),
                        split[1].substring(5), split[2].substring(3)), this.list);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BobException("Incorrect usage of command.");
        }

        if (t != null) {
            this.ui.printTaskAddMessage(t, this.list);
        }
    }

    private void handleTaskDeletion(String input) throws BobException {
        String[] args = input.split("\\s+");
        if (args.length < 2) {
            throw new BobException("The command " + args[0] + " requires a task ID.");
        }

        this.ui.printLine();
        int taskId = Integer.parseInt(args[1]) - 1;

        if (!(taskId < this.list.size()) || taskId < 0) {
            throw new BobException("The command " + args[0] + " requires a valid ID.");
        }

        Task t = this.list.get(taskId);
        String message = "        " + t.getType() + t.getStatus() + " " + t;

        this.deleteTask(taskId);
        System.out.println("    You have removed the current task:");
        System.out.println(message);
        this.ui.printList(true, this.list);
        this.ui.printLine();
    }

    public static void main(String[] args) {

        Bob bob = new Bob(
                new BobUI(new Scanner(System.in)),
                new BobStorage());

        bob.start();
    }
}
