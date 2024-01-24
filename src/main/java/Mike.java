import java.util.Scanner;
import java.util.ArrayList;
public class Mike {
    private static TaskList taskList = new TaskList();
    private static final String exitCommand = "bye";
    private static final String listCommand = "list";
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

        for (;;) {
           userInput = scanner.nextLine();
           if (userInput.equals(exitCommand)) {
               farewell();
               break;
           } else if (userInput.equals(listCommand)) {
               state(taskList);
           } else {
               addTask(userInput);
           }
        }
    }

    private static void addTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList.add(newTask);

        String reply = "added: " + taskName;
        state(reply);
    }

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

    private static void greet() {
        String greeting =
                " Hello! I'm Mike WAZOWSKI.\n" +
                " What can I do for you?";
        state(greeting);
    }

    private static void farewell() {
        String farewell =
                " Where are you going? We'll talk.\n" +
                " We'll have a latte.";
        state(farewell);
    }

    private static void state(Object message) {
        System.out.println(horizontalLine);
        System.out.println(message);
        System.out.println(horizontalLine);
    }
}
