import java.util.Scanner;

public class Saopig {
    enum Command {
        COMMAND_BYE,
        COMMAND_MARK_DONE,
        COMMAND_UNMARK_DONE,
        COMMAND_DEADLINE,
        COMMAND_EVENT,
        COMMAND_LIST,
        COMMAND_TODO,
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
                case COMMAND_MARK_DONE:
                    taskList.markTaskAsDone(Integer.parseInt(input.substring(5)) - 1);
                    break;
                case COMMAND_UNMARK_DONE:
                    taskList.unmarkTaskAsDone(Integer.parseInt(input.substring(7)) - 1);
                    break;
                case COMMAND_LIST:
                    taskList.listTasks();
                    break;
                case COMMAND_BYE:
                    bye();
                    break mainLoop;
                case COMMAND_TODO:
                    taskList.addTodoTask(input);
                    break;
                case COMMAND_EVENT:
                    taskList.addEventTask(input);
                    break;
                case COMMAND_DEADLINE:
                    taskList.addDeadlineTask(input);
                    break;
                case COMMAND_UNKNOWN:
                    speakWithHorizontalLines("Oh no! I'm sorry, but I don't know what that means.\n " +
                            "Please try again, or type 'bye' to exit.");
                    break;
            }
        }
    }

    public static Command getCommandFromString(String input) {
        String processedInput = input.trim().toUpperCase();
        String[] splitInput = processedInput.split(" ");
        switch (splitInput[0]) {
            case "UNMARK":
                return Command.COMMAND_UNMARK_DONE;
            case "MARK":
                return Command.COMMAND_MARK_DONE;
            case "EVENT":
                return Command.COMMAND_EVENT;
            case "DEADLINE":
                return Command.COMMAND_DEADLINE;
            case "TODO":
                return Command.COMMAND_TODO;
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
        speakWithHorizontalLines("Hello! I'm SAOPIG BOT\n " +
                "Oh, hello there!\n It's such a pleasure to meet you.\n " +
                "I'm just over the moon to have someone new to chat with!\n " +
                "I hope your day is as bright and cheerful as a sunny garden.\n");
    }

    public static void bye() {
        speakWithHorizontalLines("\n" +
                "As our time together comes to a close, " +
                "I just want to say it's been an absolute delight!\n " +
                "Remember, every day is a new adventure waiting to happen.\n " +
                "Bye for now, and take care! ");
    }
}
