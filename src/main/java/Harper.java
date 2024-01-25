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
    public void listTasks() {
        System.out.println(line);
        if (this.list.isEmpty()) {
            System.out.println("Nothing is in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println(i + 1 + ". " + this.list.get(i).toString());
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
        System.out.println(line + "\n" + "added: " + newTask.toString() + "\n" + line);
    }

    /**
     * Creates a todo task with the description and adds it into the list.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        Task newToDo = new ToDo(description);
        this.list.add(newToDo);
        int listSize = this.list.size();
        System.out.println(line + "\n"
                + "Got it. I've added this task into your list:\n"
                + newToDo.toString() + "\n"
                + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                + line);
    }

    /**
     * Creates a deadline task with the description and deadline and adds it into the list.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public void addDeadline(String description, String by) {
        Task newDeadline = new Deadline(description, by);
        this.list.add(newDeadline);
        int listSize = this.list.size();
        System.out.println(line + "\n"
                + "Got it. I've added this task into your list:\n"
                + newDeadline.toString() + "\n"
                + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                + line);
    }

    /**
     * Creates an event task with desciption, start time and end time, and adds it into the list.
     *
     * @param description Description of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public void addEvent(String description, String start, String end) {
        Task newEvent = new Event(description, start, end);
        this.list.add(newEvent);
        int listSize = this.list.size();
        System.out.println(line + "\n"
                + "Got it. I've added this task into your list:\n"
                + newEvent.toString() + "\n"
                + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                + line);
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
        System.out.println(taskToMark.toString() + "\n" + line);
    }

    /**
     * Informs the user that the command entered is invalid.
     */
    public void handleInvalidInput() {
        System.out.println(line + "\n" + "Please enter an valid input!\n" + line);
    }

    /**
     * Informs the user that the index entered is invalid.
     */
    public void handleIndexOutOfBounds() {
        System.out.println(line + "\n" + "Index is out of bounds. Please provide a valid index!\n" + line);
    }

    /**
     * Handles the logic when todo is entered.
     *
     * @param input The input of user.
     */
    public void handleToDO(String input) {
        String taskDescription = input.substring("todo".length()).trim();
        this.addToDo(taskDescription);
    }

    /**
     * Handles the logic when deadline is entered.
     *
     * @param input The input of user.
     */
    public void handleDeadline(String input) {
        String taskDescriptionAndDeadline = input.substring("deadline".length()).trim();
        String[] parts = taskDescriptionAndDeadline.split("/by", 2);
        String description = parts[0].trim();
        String deadline = parts[1].trim();
        this.addDeadline(description, deadline);
    }

    /**
     * Handles the logic when event is entered.
     *
     * @param input The input of user.
     */
    public void handleEvent(String input) {
        String taskDescriptionAndStartEnd = input.substring("event".length()).trim();
        String[] parts = taskDescriptionAndStartEnd.split("/from", 2);
        String description = parts[0].trim();
        String[] startAndEnd = parts[1].trim().split("/to", 2);
        String start = startAndEnd[0].trim();
        String end = startAndEnd[1].trim();
        this.addEvent(description, start, end);
    }

    /**
     * Handles the logic when mark or unmark is entered.
     *
     * @param input The input of user.
     */
    public void handleMarkOrUnmark(String input) {
        String[] commands = input.split(" ", 2);
        int taskIndex = Integer.parseInt(commands[1]) - 1;
        this.markOrUnmark(commands[0], taskIndex);
    }

    /**
     * Starts the chat, reads user's input and respond to user.
     * Saves user's input and displays back when requested.
     * Marks tasks as done or not done.
     * Add different types of tasks into the list.
     */
    public void startChat() {
        this.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                this.exit();
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                this.listTasks();
                continue;
            }

            try {
                if (input.startsWith("todo ")) {
                    this.handleToDO(input);
                    continue;
                }
                if (input.startsWith("deadline ")) {
                    this.handleDeadline(input);
                    continue;
                }

                if (input.startsWith("event ")) {
                    this.handleEvent(input);
                    continue;
                }

                if (input.startsWith("mark") || input.startsWith("unmark")) {
                    this.handleMarkOrUnmark(input);
                    continue;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                this.handleInvalidInput();
                continue;
            } catch (IndexOutOfBoundsException e) {
                this.handleIndexOutOfBounds();
                continue;
            }
            this.handleInvalidInput();
        }
    }
}
