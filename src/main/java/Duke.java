import java.util.Scanner;

/**
 * This class represents a chat application.
 */
public class Duke {
    private Scanner sc = new Scanner(System.in);
    private Task[] list;
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startChat();
    }

    /**
     * Constructs a task list of size.
     */
    private Duke() {
        this.list = new Task[100];
    }

    /**
     * Initiates the chat by invoking the sayHi() method.
     * Handles user input to display the list for "list" input, exit the chat for "bye" input,
     * marks or unmarks tasks as done or append to the list for any other input,
     * separates responses based on the type of task.
     */
    private void startChat() {
        sayHi();
        boolean exited = false;
        while (!exited) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                exited = true;
            }
            else if (userInput.equals("list")) {
                displayList();
            }
            else if (userInput.startsWith("mark ")) {
                int num = Integer.parseInt(userInput.replace("mark ", ""));
                markAsDone(num);
            }
            else if (userInput.startsWith("unmark ")) {
                int num = Integer.parseInt(userInput.replace("unmark ", ""));
                unMarkAsDone(num);
            }
            else if (userInput.startsWith("todo ")) {
                String todo = userInput.replace("todo ", "");
                appendToDo(todo);
            } else if (userInput.startsWith("deadline ")) {
                String[] deadline = userInput.replace("deadline ", "").split(" /by ");
                appendDeadline(deadline[0], deadline[1]);
            } else if (userInput.startsWith("event ")) {
                String[] event = userInput.replace("event ", "").split(" /from ");
                String[] time = event[1].split(" /to ");
                appendEvent(event[0], time[0], time[1]);
            }
        }
        sayBye();
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
        list[num - 1].markAsDone();
        System.out.println("\nNice! I've marked this task as done:\n" + list[num - 1] + "\n");
    }

    /**
     * Marks a task as not done based on the provided task number.
     *
     * @param num The task number to mark as not done.
     */
    private void unMarkAsDone(int num) {
        list[num - 1].unMarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:\n\t" + list[num - 1] + "\n");
    }

    /**
     * Displays the current list of items with their respective indices.
     * Skips null or uninitialized elements in the list.
     */
    private void displayList() {
        System.out.println();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.printf("%d. %s\n", i + 1, list[i]);
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
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = todo;
                taskResponse(todo);
                return;
            }
        }
    }

    /**
     * Appends the given deadline type input to the list at the first available slot.
     *
     * @param description The description of the task to be added to the list.
     * @param by The deadline to be added to the list.
     */
    private void appendDeadline(String description, String by) {
        Deadline deadline = new Deadline(description, by);
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = deadline;
                taskResponse(deadline);
                return;
            }
        }
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
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = event;
                taskResponse(event);
                return;
            }
        }
    }
    /**
     * Counts number of non-null tasks in the array, which are tasks given by the user
     * @param array the array of tasks
     * @return the number of non-null tasks in the given array.
     */
    private int countNonNullElements(Task[] array) {
        int count = 0;
        for (Task element : array) {
            if (element != null) {
                count++;
            }
        }
        return count;
    }
    /**
     * Prints out the response, specific to the type of task, after adding the task to the list
     * @param task the task that is added to the list.
     */
    private void taskResponse(Task task) {
        int numTasks = countNonNullElements(list);
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
}