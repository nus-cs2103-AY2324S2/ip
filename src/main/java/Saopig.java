import java.util.ArrayList;
import java.util.Scanner;

public class Saopig {
    enum Command {
        COMMAND_BYE,
        COMMAND_LIST,
        COMMAND_UNKNOWN,
        // Add more commands here in the future
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greet();
        TaskList taskList = new TaskList();
        mainLoop:
        while (true) {
            String input = scanner.nextLine();
            Command command = getCommandFromString(input);
            switch (command) {
                case COMMAND_LIST:
                    listTasks(taskList);
                    break;
                case COMMAND_BYE:
                    speakWithHorizontalLines("Bye. Hope to see you again soon!");
                    break mainLoop;
                case COMMAND_UNKNOWN:
                    addTask(taskList, input);
                    break;
            }
        }
    }

    public static void listTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            speak((i + 1) + ". " + task.getDescription());
        }
        speak("____________________________________________________________");
    }

    public static void addTask(TaskList taskList, String input) {
        Task task = new Task(input);
        taskList.addTask(task);
        speakWithHorizontalLines("added: " + input);
    }

    public static Command getCommandFromString(String input) {
        switch (input.trim().toUpperCase()) {
            case "LIST":
                return Command.COMMAND_LIST;
            case "BYE":
                return Command.COMMAND_BYE;
            default:
                return Command.COMMAND_UNKNOWN;
        }
    }

    // speak to user
    public static void speakWithHorizontalLines(String str) {
        System.out.println(str);
        System.out.println("____________________________________________________________");
    }

    public static void speak(String str) {
        System.out.println(str);
    }

    // greet user
    public static void greet() {
        String logo = " ____    _    ___  ____ ___ ____   ____   ___ _____ \n" +
                "/ ___|  / \\  / _ \\|  _ \\_ _/ ___| | __ ) / _ \\_   _|\n" +
                "\\___ \\ / _ \\| | | | |_) | | |  _  |  _ \\| | | || |  \n" +
                " ___) / ___ \\ |_| |  __/| | |_| | | |_) | |_| || |  \n" +
                "|____/_/   \\_\\___/|_|  |___\\____| |____/ \\___/ |_|\n";
        speakWithHorizontalLines(logo);
        speakWithHorizontalLines("Hello! I'm SAOPIG BOT\nWhat can I do for you?");
    }
}
