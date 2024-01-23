import java.util.Scanner;

public class Ellie {

    final static String horizontalLine = "____________________________________________________________";
    final static String logoEllie =
            " _______   ___       ___       ___  _______          \n" +
            "|\\  ___ \\ |\\  \\     |\\  \\     |\\  \\|\\  ___ \\         \n" +
            "\\ \\   __/|\\ \\  \\    \\ \\  \\    \\ \\  \\ \\   __/|        \n" +
            " \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    \\ \\  \\ \\  \\_|/__      \n" +
            "  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____\\ \\  \\ \\  \\_|\\ \\     \n" +
            "   \\ \\_______\\ \\_______\\ \\_______\\ \\__\\ \\_______\\    \n" +
            "    \\|_______|\\|_______|\\|_______|\\|__|\\|_______|    \n";

    enum Command {
        MARK,
        UNMARK,
        LIST,
        DEADLINE,
        EVENT,
        TODO,
        UNKNOWN,
        HELP,
        BYE,

    }


    private Tracker tracker;

    public Ellie() {
        tracker = new Tracker();
    }

    public void start() {
        hello();
        Scanner reader = new Scanner(System.in);
        Command command = Command.UNKNOWN;
        String input = reader.nextLine();

        while ( true ) {

            String[] inputArray = input.split(" ", 2);
            String stringHeader = inputArray[0].toLowerCase();

            switch (stringHeader) {
                case "list":
                    command = Command.LIST;
                    break;
                case "mark":
                    command = Command.MARK;
                    break;
                case "unmark":
                    command = Command.UNMARK;
                    break;
                case "todo":
                    command = Command.TODO;
                    break;
                case "deadline":
                    command = Command.DEADLINE;
                    break;
                case "event":
                    command = Command.EVENT;
                    break;
                case "help":
                    command = Command.HELP;
                    break;
                case "bye":
                    command = Command.BYE;
                    break;
                default:
                    command = Command.UNKNOWN;
            }

            // commands with no argument: BYE / LIST / UNKNOWN
            if (command == Command.BYE) {
                break;
            }
            else if (command == Command.LIST) {
                tracker.listTasks();
                input = reader.nextLine();
                continue;
            }
            else if (command == Command.HELP) {
                System.out.println("Here's a list of supported commands so far:" +
                        "\n help \n list \n mark/unmark [int] \n todo [task] \n " +
                        "deadline [task] /by [date]  \n event [task] /from [date] /to [date] \n bye \n");
                input = reader.nextLine();
                continue;
            }
            else if (command == Command.UNKNOWN) {
                try {
                    throw new UnknownInputException("Command Unknown or Missing");
                } catch (UnknownInputException e) {
                    System.out.println("Sorry! Not sure what you're referring to (╥_╥)");
                    System.out.println("Type 'help' to view the list of supported commands!\n");
                    input = reader.nextLine();
                    continue;
                }
            }

            // check for following input argument
            try {
                if (inputArray.length < 2) {
                    throw new InvalidTaskInputException("command contains no argument");
                }
            } catch (InvalidTaskInputException e) {
                System.out.println("Please input an argument! \n [command] [argument]");
                input = reader.nextLine();
                continue;
            }

            String stringBody = inputArray[1];

            if (command == Command.MARK) {
                if (isNumeric(stringBody)) {
                    int index = Integer.parseInt(stringBody);
                    tracker.markTaskIndex(index);
                } else {
                    System.out.println("Input a valid number to mark! \n Usage: mark [int]");
                }
            }
            else if (command == Command.UNMARK) {
                if (isNumeric(stringBody)) {
                    int index = Integer.parseInt(stringBody);
                    tracker.unmarkTaskIndex(index);
                } else {
                    System.out.println("Input a valid number to unmark! \n Usage: unmark [int]");
                }
            }
            else if (command == Command.TODO) {
                Task task = new Todo(stringBody);
                tracker.addTask(task);
            }
            else if (command == Command.DEADLINE) {
                if (!stringBody.contains("/by")) {
                    System.out.println("Please add a due date for your dateline using '/by'!");
                } else {
                    String[] deadlineArray = stringBody.split("/by");
                    String event = deadlineArray[0].trim();
                    String dueDate = deadlineArray[1].trim();
                    if (event.equals("")) {
                        System.out.println("Please add event name.");
                    } else if (dueDate.equals("")) {
                        System.out.println("Please add a deadline!");
                    } else {
                        Task task = new Deadline(event, dueDate);
                        tracker.addTask(task);
                    }
                }
            }
            else if (command == Command.EVENT) {
                if (!stringBody.contains("/from")) {
                    System.out.println("Please add a start date for your event using '/from'!");
                }
                else if (!stringBody.contains("/to")) {
                    System.out.println("Please add an end date for your event using '/to'!");
                }
                else {
                    String[] deadlineArray = stringBody.split("/from");
                    String event = deadlineArray[0].trim();
                    String eventDuration = deadlineArray[1];
                    if (event.equals("")) {
                        System.out.println("Please add event name.");
                    } else if (event.contains("/end")) {
                        System.out.println("Please place /end [end time] after /from [start time]!");
                    } else {
                        String[] duration = eventDuration.split("/to");
                        Task task = new Event(event, duration[0].trim(), duration[1].trim());
                        tracker.addTask(task);
                    }
                }

            }

            input = reader.nextLine();
        }

        goodbye();
    }

    private static Boolean isNumeric(String string) {
        return string.matches("\\d+");
    }

    private void hello() {
        System.out.println("Hello! I'm Ellie, your CS2103T chat bot! I help by tracking your tasks!");
        System.out.println(logoEllie + "\n" + horizontalLine);
        System.out.println("What can I do for you? Type 'help' to see available commands! \n");
    }

    private void goodbye() {
        System.out.println("\n Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


}
