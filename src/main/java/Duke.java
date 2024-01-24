import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a chat application.
 */
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }

    /**
     * Constructs a task list of size.
     */
    private Duke() {
        this.list = new ArrayList<>();
    }

    /**
     * Initiates the chat by invoking the sayHi() method.
     * Handles user input to display the list for "list" input, exit the chat for "bye" input,
     * marks or unmarks tasks as done, deletes tasks, or append to the list for to-do/deadline/event input,
     * separates responses based on the type of task.
     */
    private void startChat() {
        sayHi();
        boolean exited = false;
        while (!exited) {
            String userInput = sc.nextLine();
            try {
                if (userInput.equals("bye")) {
                    exited = true;
                } else if (userInput.equals("list")) {
                    displayList();
                } else if (userInput.startsWith("mark")) {
                    int num = parseTaskNumber(userInput, "mark");
                    markAsDone(num);
                } else if (userInput.startsWith("unmark")) {
                    int num = parseTaskNumber(userInput, "unmark");
                    unMarkAsDone(num);
                } else if (userInput.startsWith("todo")) {
                    String todo = userInput.replace("todo", "").trim();
                    processEmptyDescription(todo, "todo");
                    appendToDo(todo);
                } else if (userInput.startsWith("deadline")) {
                    String[] deadline = parseDeadlineInput(userInput, "deadline");
                    appendDeadline(deadline[0], deadline[1]);
                } else if (userInput.startsWith("event")) {
                    String[] event = parseEventInput(userInput, "event");
                    appendEvent(event[0], event[1], event[2]);
                } else if(userInput.startsWith("delete")) {
                    int num = parseTaskNumber(userInput, "delete");
                    deleteTask(num);
                } else {
                    throw new DukeException("\nError! I don't know what that means. Types of tasks are limited to ToDos, Deadlines and Events.\n");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sayBye();
    }

    /**
     * Processes and validates that the description is not empty.
     * Throws a DukeException if the description is empty.
     *
     * @param description The description to be checked.
     * @param task        The type of task.
     * @throws DukeException If the description is empty.
     */
    private void processEmptyDescription(String description, String task) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("\nError! The description of a " + task + " cannot be empty.\n");
        }
    }

    /**
     * Parses the task number from the user input and validates it.
     * Throws a DukeException for empty task number or invalid task number that is outside the indexes of the list.
     *
     * @param input   The user input containing the task number.
     * @param command The type of task.
     * @return The parsed and validated task number.
     * @throws DukeException If the task number is invalid or empty.
     */
    private int parseTaskNumber(String input, String command) throws DukeException {
        String taskNumString = input.replace(command, "").trim();
        if (taskNumString.isEmpty() || !taskNumString.matches("\\d+")) {
            throw new DukeException("\nError! Please provide a valid task number after '" + command + "'.\n");
        }
        int taskNumber = Integer.parseInt(taskNumString);

        if (taskNumber < 1 || taskNumber > list.size() || list.get(taskNumber - 1) == null) {
            throw new DukeException("\nError! Task number '" + taskNumber + "' does not exist.\n");
        }

        return taskNumber;
    }

    /**
     * Parses and validates the user input for deadline tasks.
     * Throws a DukeException for invalid input or wrong formatting.
     *
     * @param input   The user input containing the description and deadline.
     * @param command The type of task.
     * @return An array containing the parsed description and deadline.
     * @throws DukeException If the input is invalid or has wrong formatting.
     */
    private String[] parseDeadlineInput(String input, String command) throws DukeException {
        String[] deadlineInput = input.replace(command, "").trim().split(" /by ");

        if (deadlineInput.length != 2) {
            throw new DukeException("\nError! Please provide a valid description and deadline after '" + command + "'.\n");
        }

        String description = deadlineInput[0].trim();
        String by = deadlineInput[1].trim();

        processEmptyDescription(description, "deadline");
        processEmptyDescription(by, "deadline");

        return new String[]{description, by};
    }

    /**
     * Parses and validates the user input for event tasks.
     * Throws a DukeException for invalid input or wrong formatting.
     *
     * @param input   The user input for event tasks.
     * @param command The type of task.
     * @return An array containing the parsed description, start time, and end time.
     * @throws DukeException If the input is invalid or has wrong formatting.
     */
    private String[] parseEventInput(String input, String command) throws DukeException {
        String[] eventInput = input.replace(command, "").trim().split(" /from ");

        if (eventInput.length != 2) {
            throw new DukeException("\nError! Please provide a valid description, start time, and end time after '" + command + "'.\n");
        }

        String description = eventInput[0].trim();
        String[] timeInput = eventInput[1].split(" /to ");

        if (timeInput.length != 2) {
            throw new DukeException("\nError! Please provide a valid start time and end time after '/from' and '/to'.\n");
        }

        String startTime = timeInput[0].trim();
        String endTime = timeInput[1].trim();

        processEmptyDescription(description, "event");
        processEmptyDescription(startTime, "event");
        processEmptyDescription(endTime, "event");

        return new String[]{description, startTime, endTime};
    }

    /**
     * Displays a starting message to greet the user.
     */
    private void sayHi() {
        System.out.println("Hello! I'm myChats\n" + "What can I do for you?\n");
    }

    /**
     *  Displays an exit message.
     */
    private void sayBye() {
        System.out.println("\nBye. Hope to see you again soon!");
    }

    /**
     * Marks a task as done based on the provided task number.
     *
     * @param num The task number to mark as done.
     */
    private void markAsDone(int num) {
        list.get(num - 1).markAsDone();
        System.out.println("\nNice! I've marked this task as done:\n" + list.get(num - 1) + "\n");
    }

    /**
     * Marks a task as not done based on the provided task number.
     *
     * @param num The task number to mark as not done.
     */
    private void unMarkAsDone(int num) {
        list.get(num - 1).unMarkAsDone();
        System.out.println("\nOK, I've marked this task as not done yet:\n\t" + list.get(num - 1) + "\n");
    }

    /**
     * Displays the current list of items with their respective indices.
     * Skips null or uninitialized elements in the list.
     */
    private void displayList() {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.printf("%d. %s\n", i + 1, list.get(i));
            }
        }
        System.out.println();
    }

    /**
     * Appends the given to-do type input to the list at the first available slot.
     *
     * @param input The to-do type input to be added to the list.
     */
    private void appendToDo(String input) {
        Todo todo = new Todo(input);
        list.add(todo);
        taskResponse(todo);
    }

    /**
     * Appends the given deadline type input to the list at the first available slot.
     *
     * @param description The description of the task to be added to the list.
     * @param by The deadline to be added to the list.
     */
    private void appendDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        list.add(deadline);
        taskResponse(deadline);
    }

    /**
     * Appends the given event type input to the list at the first available slot.
     *
     * @param description The description to be added to the list.
     * @param startTime The start timing to be added to the list.
     * @param endTime The end timing to be added to the list.
     */
    private void appendEvent(String description, String startTime, String endTime) {
        Event event = new Event(description, startTime, endTime);
        list.add(event);
        taskResponse(event);
    }

    /**
     * Prints out the response, specific to the type of task, after adding the task to the list
     * @param task the task that is added to the list.
     */
    private void taskResponse(Task task) {
        int numTasks = list.size();
        System.out.println();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        }
        if (numTasks != 1) {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
        System.out.println();
    }

    /**
     * Deletes a task from the task list based on the provided one-based index.
     * Throws a DukeException if the provided index is invalid.
     *
     * @param oneItem The one-based index of the task to be deleted.
     * @throws DukeException If the provided index is out of bounds or corresponds to a null task.
     */
    private void deleteTask(int oneItem) throws DukeException {
        int zeroItem = oneItem - 1;
        if (zeroItem >= 0 && zeroItem < list.size() && list.get(zeroItem) != null) {
            Task deletedTask = list.remove(zeroItem);
            deleteResponse(deletedTask);
        } else {
            throw new DukeException("\nError! Task number '" + oneItem + "' does not exist.\n");
        }
    }

    /**
     * Displays the response message after deleting a task if the deletion is successful and an exception has not been thrown.
     *
     * @param task The task that has been deleted.
     */
    private void deleteResponse(Task task) {
        int numTasks = list.size();
        System.out.println();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        }
        if (numTasks != 1) {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
        System.out.println();
    }
}