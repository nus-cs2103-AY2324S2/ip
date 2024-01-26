import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    private ArrayList<Task> list;
    private Scanner scanner;
    private static final String TERMINATE_COMMAND = "bye";

    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";


    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";

    private static final String HOME_BASE_PATH = System.getProperty("user.home");
    private static final String NEW_LINE = System.lineSeparator();
    private static final File saveData = new File(Bob.HOME_BASE_PATH + "/save.txt");

    public Bob() {
        this.scanner = new Scanner(System.in);
        this.list = new ArrayList<>();
    }

    /**
     * Method for getting user input.
     */
    private String getUserInput() {
        return this.scanner.nextLine();
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
    private Task addItem(Task t) throws BobException {
        this.list.add(t);
        this.updateTaskList();
        return t;
    }

    private void deleteTask(int taskId) {
        this.list.remove(taskId);
        this.updateTaskList();
    }

    /**
     * Mark item done.
     */
    private void markDone(int item) {
        this.list.get(item).updateStatus(true);
        this.updateTaskList();
    }

    /**
     * Mark item undone.
     */
    private void markUndone(int item) {
        this.list.get(item).updateStatus(false);
        this.updateTaskList();
    }

    /**
     * List items in list.
     */
    private void printList(boolean summarized) {

        if (!summarized) {

            this.printLine();

            System.out.println("    Here are the tasks in your list:");

            for (int i = 0; i < this.list.size(); i++) {
                Task task = this.list.get(i);
                System.out.println("    " + (i + 1) + "." + task.getType()
                        + task.getStatus() + " " + task);
            }
            this.printLine();
        } else {
            System.out.println("    You have " + this.list.size()
                    + " tasks in your list.");
        }
    }

    private void updateTaskList()  {
        try {
            if (!saveData.exists()) {
                this.instantiateDirectory();
            }
            FileWriter fileWriter = new FileWriter(Bob.saveData, false);
            for (Task t : this.list) {
                fileWriter.write(t.toSavableFormat() + Bob.NEW_LINE);
            }
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("An error occurred when trying to access the save file. "
                    + "Please ensure that the application has permissions to write and read from your HOME directory.");
        }
    }

    private void loadSavedTasks() throws BobException.FileAccessError, BobException.CorruptedSaveData {

        try {

            if (!saveData.exists()) {
                this.instantiateDirectory();
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(saveData))) {

                String line;
                boolean invalidFile = false;

                while ((line = br.readLine()) != null) {

                    String[] properties = line.split("\\|");

                    if (properties.length < 4) {
                        invalidFile = true;
                    }

                    String taskType = properties[1];

                    if (!invalidFile
                            && !(taskType.equals("T")
                            || taskType.equals("E")
                            || taskType.equals("D"))) {
                        invalidFile = true;
                    }

                    if (!invalidFile && !(properties[3].equals("false")
                            || properties[3].equals("true"))) {
                        invalidFile = true;
                    }

                    if (invalidFile) {
                        throw new BobException
                                .CorruptedSaveData("Save file is corrupt. The application will create a new save file.");
                    }

                    if (taskType.equals("T") && properties.length < 4
                            || taskType.equals("E") && properties.length < 6
                            || taskType.equals("D") && properties.length < 5) {
                        throw new BobException
                                .CorruptedSaveData("Tasks are corrupt. The application will create a new save file.");
                    }

                    try {

                        switch (taskType) {
                        case "T":
                            this.addItem(new Task(properties[2])
                                            .setUUID(properties[0])
                                            .updateStatus(properties[3].equals("true")));
                            break;
                        case "E":
                            this.addItem(new Event(properties[2], properties[4], properties[5])
                                            .setUUID(properties[0])
                                            .updateStatus(properties[3].equals("true")));
                            break;
                        case "D":
                            this.addItem(new Deadline(properties[2], properties[4])
                                            .setUUID(properties[0])
                                            .updateStatus(properties[3].equals("true")));
                            break;
                        }

                    } catch (BobException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (BobException.FileAccessError e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new BobException
                    .FileAccessError("An error occurred when trying to access the save file. "
                    + "Please ensure that the application has permissions to write and read from your HOME directory.");
        }

    }

    private void instantiateDirectory() throws BobException.FileAccessError {
        try {
            Bob.saveData.createNewFile();
        } catch (IOException e) {
            throw new BobException.FileAccessError("An error occurred when trying to access the save file. "
                    + "Please ensure that the application has permissions to write and read from your HOME directory.");
        }
    }

    /**
     * Main logic of the chatbot.
     */
    public void start() {

        try {
            this.loadSavedTasks();
        } catch (BobException.FileAccessError e) {
            System.out.println(e.getMessage());
        } catch (BobException.CorruptedSaveData e) {
            System.out.println(e.getMessage());
        }

        this.greet();

        while (this.scanner.hasNextLine()) {

            String input = this.getUserInput().trim();
            final String command = input.split(" ")[0];

            try {
                switch (command) {
                case Bob.TERMINATE_COMMAND:
                    this.terminate();
                    System.exit(0);
                    break;
                case Bob.LIST_COMMAND:
                    this.printList(false);
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

        String[] args = input.split(" ");
        if (args.length < 2) {
            throw new BobException("The command " + args[0] + " requires a task ID.");
        }

        this.printLine();
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
        this.printLine();
    }

    private void handleTaskCreation(String input) throws BobException {

        Task t = null;

        try {
            if (input.contains(Bob.TODO_COMMAND)) {

                if (input.length() == Bob.TODO_COMMAND.length()) {
                    throw new BobException("The command " + Bob.TODO_COMMAND + " requires a task description.");
                }

                String description = input.substring(Bob.TODO_COMMAND.length() + 1);

                t = this.addItem(new Task(description));
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

                String[] split = input.split("/");
                if (split.length < 2) {
                    throw new BobException("The command " + Bob.DEADLINE_COMMAND
                            + " requires both a task description and a deadline.");
                }

                t = this.addItem(new Deadline(split[0].substring(0, split[0].length() - 1),
                        split[1].substring(3)));
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

                t = this.addItem(new Event(split[0].substring(0, split[0].length() - 1),
                        split[1].substring(5), split[2].substring(3)));
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BobException("Incorrect usage of command.");
        }

        if (t != null) {
            this.printTaskAddMessage(t);
        }
    }

    private void handleTaskDeletion(String input) throws BobException {
        String[] args = input.split(" ");
        if (args.length < 2) {
            throw new BobException("The command " + args[0] + " requires a task ID.");
        }

        this.printLine();
        int taskId = Integer.parseInt(args[1]) - 1;

        if (!(taskId < this.list.size()) || taskId < 0) {
            throw new BobException("The command " + args[0] + " requires a valid ID.");
        }

        Task t = this.list.get(taskId);
        String message = "        " + t.getType() + t.getStatus() + " " + t;

        this.deleteTask(taskId);
        System.out.println("    You have removed the current task:");
        System.out.println(message);
        this.printList(true);
        this.printLine();
    }

    private void printTaskAddMessage(Task t) {
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
