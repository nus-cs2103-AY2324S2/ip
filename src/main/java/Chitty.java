import java.util.Scanner;

public class Chitty {
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s \nWhat can I do for you?\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String MARK_TASK = "Nice! I've marked this task as done: \n";
    private static final String UNMARK_TASK = "OK, I've marked this task as not done yet: \n";
    private static final String SPACING = "---------------------------------------------------\n";
    private static final TaskList taskList = new TaskList();



    private static void greet() {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING);
    }

    private static void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        taskList.addTask(newTask);
        System.out.println(SPACING + "added: " + newTask + "\n" + SPACING);
    }

    private static void listTasks() {
        System.out.println(SPACING + taskList + SPACING);
    }

    private static void markTask(int i) {
        Task updatedTask = taskList.markTask(i - 1);
        System.out.println(SPACING + MARK_TASK + updatedTask + "\n" + SPACING);
    }

    private static void unmarkTask(int i) {
        Task updatedTask = taskList.unmarkTask(i - 1);
        System.out.println(SPACING + UNMARK_TASK + updatedTask + "\n" + SPACING);
    }

    private static void bye() {
        System.out.println(SPACING + GOODBYE_MESSAGE + SPACING);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0].toUpperCase();
            switch (command) {
                case "LIST":
                    listTasks();
                    break;
                case "BYE":
                    bye();
                    return;
                case "MARK":
                    markTask(Integer.parseInt(input.split(" ")[1]));
                    break;
                case "UNMARK":
                    unmarkTask(Integer.parseInt(input.split(" ")[1]));
                    break;
                default:
                    addTask(input);
                    break;
            }
        }

    }
}
