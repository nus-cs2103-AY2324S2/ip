import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot called Harper.
 *
 * @author gosongying
 * @version CS2103T AY23/24 Sem 2
 */
public class Harper {

    String line = "_________________________________________________________";
    ArrayList<Task> list = new ArrayList<>();

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(line + "\n"
                + "Hello! I am Harper.\n"
                + "What can I do for you?\n"
                + line);
    }

    /**
     * Exit the chat.
     */
    public void exit() {
        System.out.println(line + "\n"
                + "Hope to see you again soon! Peace out!\n"
                + line);
    }

    /**
     * Lists out the tasks in the list.
     */
    public void list() {
        System.out.println(line);
        if (this.list.isEmpty()) {
            System.out.println("Nothing is in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(i + 1 + ". " + this.list.get(i).getStatus());
            }
        }
        System.out.println(line);
    }

    /**
     * Adds task into the list.
     *
     * @param input The description of the task added.
     */
    public void addTask(String input) {
        Task newTask = new Task(input);
        this.list.add(newTask);
        System.out.println(line + "\n" + "added: " + newTask.getStatus() + "\n" + line);
    }

    /**
     * Marks the task as done or not done based on the command.
     *
     * @param command Marks or unmarks the task.
     * @param taskIndex Index of the task in the list.
     */
    public void markOrUnmark(String command, int taskIndex) {
        Task taskToMark = list.get(taskIndex);
        System.out.println(line);
        if (command.equals("mark")) {
            taskToMark.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        }
        if (command.equals("unmark")) {
            taskToMark.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskToMark.getStatus() + "\n" + line);
    }

    /**
     * Starts the chat, reads user's input and respond to user.
     * Saves user's input and displays back when requested.
     * Marks tasks as done or not done.
     */
    public void startChat() {
        this.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                this.exit();
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                this.list();
                continue;
            }

            String[] commands = input.split(" ");
            if (commands.length == 2) {
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    if (commands[0].equals("mark") || commands[0].equals("unmark")) {
                        this.markOrUnmark(commands[0], taskIndex);
                    }
                } catch (NumberFormatException e) {
                    this.addTask(input);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(line + "\n" + "Index is out of bounds. Please provide a valid index!\n" + line);
                }
            } else {
                this.addTask(input);
            }
        }
    }
}
