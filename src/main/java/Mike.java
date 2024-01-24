import java.util.Scanner;
public class Mike {
    private static TaskList taskList = new TaskList();
    private static final String exitCommand = "bye";
    private static final String listCommand = "list";
    private static final String markCommand = "mark";
    private static final String unmarkCommand = "unmark";
    private static final String horizontalLine = "=================================";

    /**
     * Main method that runs the program.
     * @param args n/a
     */
    public static void main(String[] args) {
        logo();
        greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        label:
        for (;;) {
           userInput = scanner.next();
            switch (userInput) {
                case exitCommand:
                    farewell();
                    break label;
                case listCommand:
                    state(taskList);
                    break;
                case markCommand: {
                    int taskIndex = scanner.nextInt() - 1;
                    mark(taskIndex);
                    scanner.nextLine();
                    break;
                }
                case unmarkCommand: {
                    int taskIndex = scanner.nextInt() - 1;
                    unmark(taskIndex);
                    scanner.nextLine();
                    break;
                }
                default:
                    userInput += scanner.nextLine();
                    addTask(userInput);
                    break;
            }
        }
    }

    /**
     * Mark the task at taskIndex as done.
     * @param taskIndex The index of the task in taskList.
     */
    private static void mark(int taskIndex) {
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        String reply =
                "Nice! I've marked this task as done:\n" +
                "  " + task.toString();
        state(reply);
    }

    /**
     * Unmark the task at taskIndex as not done.
     * @param taskIndex The index of the task in taskList.
     */
    private static void unmark(int taskIndex) {
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        String reply =
                "I've marked this task as not done:\n" +
                "  " + task.toString();
        state(reply);
    }

    /**
     * Adds a Task to TaskList.
     */
    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        taskList.add(newTask);

        String reply = "added: " + newTask.getDescription();
        state(reply);
    }

    /**
     * Prints the Wazowski logo as ASCII art.
     */
    private static void logo() {
        String logo =
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣴⣾⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⠃⣽⡅⢲⡎⢩⣯⠑⡄⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⠢⣉⣥⣦⣤⣬⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⠰⢿⣿⣿⣿⡿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀\n" +
                "⠀⢀⣾⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⡄⠀\n" +
                "⠀⢸⣿⢿⣿⣿⣿⣿⣿⣶⣛⡛⠛⠒⠒⠒⠒⠀⠀⠀⠀⢀⣶⣿⣷⠁⠀\n" +
                "⠀⣿⡇⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣾⡇⠀⢀⣼⣿⠟⠸⠄⠀\n" +
                "⠀⡟⠁⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀⠀\n" +
                "⠀⡇⠀⠀⠀⠀⠈⣿⠛⠻⠿⢿⣿⣿⣿⣿⣿⠿⢿⣿⠁⠀⠀⠀⠀⢸⠀\n" +
                "⠀⡇⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀⠀⢸⠀\n" +
                "⠀⡇⠀⠀⠀⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠈⢆\n" +
                "⢰⡇⠰⡀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⡆⢸\n" +
                "⠘⢣⠀⠁⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⢠⠟\n" +
                "⠀⠁⠓⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠃⠀⠀⠀⠀⠀⠁⠀\n" +
                "⠀⠀⠀⠀⣀⠇⢀⣷⢀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⠐⠆⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠁⠀⠀⠀";
        System.out.println(logo);
    }

    /**
     * Prints a greeting message with Wazowski pizazz.
     */
    private static void greet() {
        String greeting =
                " Hello! I'm Mike WAZOWSKI.\n" +
                " What can I do for you?";
        state(greeting);
    }

    /**
     * Prints a farewell message with Wazowski pizazz.
     */
    private static void farewell() {
        String farewell =
                " Where are you going? We'll talk.\n" +
                " We'll have a latte.";
        state(farewell);
    }

    /**
     * Prints the object with formatting.
     */
    public static void state(Object object) {
        System.out.println(horizontalLine);
        System.out.println(object);
        System.out.println(horizontalLine);
    }
}
