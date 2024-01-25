import java.util.Scanner;
public class Henry {
    private static Task[] items = new Task[100];
    private static int numOfItems = 0;
    public static void greet() {
        String logo = "  _    _                       \n" +
                " | |  | |                      \n" +
                " | |__| | ___ _ __  _ __ _   _ \n" +
                " |  __  |/ _ \\ '_ \\| '__| | | |\n" +
                " | |  | |  __/ | | | |  | |_| |\n" +
                " |_|  |_|\\___|_| |_|_|   \\__, |\n" +
                "                          __/ |\n" +
                "                         |___/ \n";
        String greetMessage = "Hello! I'm Henry\nWhat can I do for you?";
        System.out.println(logo);
        System.out.println(greetMessage);
        System.out.println();
    }
    public static void bye() {
        System.out.println("See you again bro!");
    }
    public static void addTask(Task task) {
        items[numOfItems] = task;
        numOfItems++;
        System.out.println("Added this task");
        System.out.println(items[numOfItems - 1]);
        System.out.printf("Now you have %d tasks in the list :(\n", numOfItems);
        System.out.println();
    }
    public static void markTask(int index) throws HenryException {
        if (index < 0 || index >= numOfItems) {
            throw new HenryException("The index is out of bounds!");
        }
        items[index].markAsDone();
        System.out.println("This task is marked as done XD");
        System.out.println(items[index]);
        System.out.println();
    }
    public static void unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= numOfItems) {
            throw new HenryException("The index is out of bounds!");
        }
        items[index].unmarkAsDone();
        System.out.println("This task is marked as undone :(");
        System.out.println(items[index]);
        System.out.println();
    }
    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String currentMessage = scanner.nextLine();
            if (currentMessage.equals("bye")) {
                bye();
                break;
            } else if (currentMessage.equals("list")) {
                System.out.println("Here is a list of tasks:");
                for (int i = 0; i < numOfItems; i = i + 1) {
                    System.out.printf("%d. %s\n", i + 1, items[i]);
                }
                System.out.println();
            } else if (currentMessage.startsWith("mark")) {
                try {
                    if (currentMessage.length() < 5) {
                        throw new HenryException("No index provided!");
                    }
                    int index = Integer.parseInt(currentMessage.split(" ")[1]) - 1;
                    markTask(index);
                } catch (HenryException e) {
                    System.err.println(e);
                }
            } else if (currentMessage.startsWith("unmark")) {
                try {
                    if (currentMessage.length() < 7) {
                        throw new HenryException("No index provided!");
                    }
                    int index = Integer.parseInt(currentMessage.split(" ")[1]) - 1;
                    unmarkTask(index);
                } catch (HenryException e) {
                    System.err.println(e);
                }
            } else {
                if (currentMessage.startsWith("todo")) {
                    try {
                        if (currentMessage.length() <= 5) {
                            throw new HenryException("The description of todo cannot be empty");
                        }
                        String description = currentMessage.substring(5);
                        addTask(new Todo(description));
                    } catch (HenryException e) {
                        System.err.println(e);
                    }
                } else if (currentMessage.startsWith("deadline")) {
                    try {
                        if (!currentMessage.contains("/by")) {
                            throw new HenryException("When this has to be done by?");
                        }
                        String[] temp = currentMessage.substring(9).split(" /by ");
                        addTask(new Deadline(temp[0], temp[1]));
                    } catch (HenryException e) {
                        System.err.println(e);
                    }
                } else if (currentMessage.startsWith("event")) {
                    try {
                        if (!currentMessage.contains("/from") || !currentMessage.contains("/to")) {
                            throw new HenryException("Please provide /from and /to.");
                        }
                        String[] temp = currentMessage.substring(6).split(" /from | /to ");
                        addTask(new Event(temp[0], temp[1], temp[2]));
                    } catch (HenryException e) {
                        System.err.println(e);
                    }
                } else {
                    try {
                        throw new HenryException("I don't understand this command...");
                    } catch (HenryException e) {
                        System.err.println(e);
                    }
                }
            }
        }
    }
}