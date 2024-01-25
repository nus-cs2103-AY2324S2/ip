import java.util.Scanner;

public class Chitty {
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s \nWhat can I do for you?\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String SPACING = "---------------------------------------------------\n";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final TaskList taskList = new TaskList();

    private static void greet() {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING);
    }

    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        taskList.addTask(newTask);
        System.out.println(SPACING + newTask + "\n" + SPACING);
    }

    private static void listTasks() {
        System.out.println(SPACING + taskList + SPACING);
    }

    private static void bye() {
        System.out.println(SPACING + GOODBYE_MESSAGE + SPACING);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String taskDescription = scanner.nextLine();
            if (taskDescription.equals(BYE)) {
                break;
            } else if (taskDescription.equals(LIST)) {
                listTasks();
            } else {
                addTask(taskDescription);
            }
        }
        bye();
    }
}
