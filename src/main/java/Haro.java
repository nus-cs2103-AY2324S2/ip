import java.util.Scanner;
import java.lang.Integer;
import java.lang.NumberFormatException;
public class Haro {
    private String haroLogo = " ___  ___  ________  ________  ________\n"
            + "|\\  \\|\\  \\|\\   __  \\|\\   __  \\|\\   __  \\\n"
            + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\\n"
            + " \\ \\   __  \\ \\   __  \\ \\   _  _\\ \\  \\\\\\  \\\n"
            + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\\n"
            + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\\n"
            + "    \\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\|_______|\n";
    private String horizontalLine = "______________________________________________";
    private String openingMsg = "Heya! I'm Haro!\n" + "What can I do for you today?";
    private String closingMSg = "Bye. Hope to see you some time soon!";
    private List list;
    public Haro() {
        this.list = new List();
    }

    enum Instruction {
        BYE,
        NONE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,

    }

    public void initialise() {
        greet();
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();
        Instruction instruction = Instruction.NONE;

        while (true) {
            String[] inputArr = input.split(" ", 2);
            String instructWord = inputArr[0].toLowerCase();

            switch (instructWord) {
                case "bye":
                    instruction = Instruction.BYE;
                    break;
                case "list":
                    instruction = Instruction.LIST;
                    break;
                case "mark":
                    instruction = Instruction.MARK;
                    break;
                case "unmark":
                    instruction = Instruction.UNMARK;
                    break;
                case "todo":
                    instruction = Instruction.TODO;
                    break;
                case "deadline":
                    instruction = Instruction.DEADLINE;
                    break;
                case "event":
                    instruction = Instruction.EVENT;
                    break;
                default:
                    instruction = Instruction.NONE;
            }

            if (instruction == Instruction.BYE) {
                break;
            }

            else if (instruction == Instruction.NONE) {
                try {
                    if (inputArr[0].equals("")) {
                        throw new EmptyCommandException("Empty command");
                    } else {
                        throw new InvalidCommandException("Invalid command");
                    }
                } catch (EmptyCommandException e) {
                    System.out.println("Empty command! Type something!\n ");
                    input = inputScanner.nextLine();
                    continue;
                } catch (InvalidCommandException e) {
                    System.out.println("Sorry, please type a valid command\n");
                    input = inputScanner.nextLine();
                    continue;
                }
            }

            else if (instruction == Instruction.LIST) {
                list.printList();
                input = inputScanner.nextLine();
                continue;
            }

            // Commands with arguments
            try {
                if (inputArr.length < 2 || inputArr[1].equals("")) {
                    throw new EmptyTaskException("Missing task name");
                }
            } catch (EmptyTaskException e) {
                System.out.println("Please input a task name\n");
                input = inputScanner.nextLine();
                continue;
            }

            String commandArg = inputArr[1];

            if (instruction == Instruction.MARK) {
                try {
                    if (!isNumeric(commandArg)) {
                        throw new InvalidArgsException("Please input a number for the task you want to mark!\n");
                    }

                    int taskNumber = Integer.parseInt(commandArg) - 1;
                    list.markTask(taskNumber);

                } catch (InvalidArgsException e) {
                    System.out.println(e.getMessage());
                    input = inputScanner.nextLine();
                    continue;
                }
            }

            else if (instruction == Instruction.UNMARK) {
                try {
                    if (!isNumeric(commandArg)) {
                        throw new InvalidArgsException("Please input a number for the task you want to unmark!\n");
                    }

                    int taskNumber = Integer.parseInt(commandArg) - 1;
                    list.unmarkTask(taskNumber);
                } catch (InvalidArgsException e) {
                    System.out.println(e.getMessage());
                    input = inputScanner.nextLine();
                    continue;
                }
            }

            else if (instruction == Instruction.TODO) {
                Task newTodo = new ToDo(commandArg.trim());
                list.addTask(newTodo);
            }

            else if (instruction == Instruction.DEADLINE) {
                try {
                    if (!commandArg.contains("/by")) {
                        throw new MissingDuedateException("Please specify a due date using '/by'!\n");
                    }

                    String[] taskArr = commandArg.split("/by", 2);
                    String taskName = taskArr[0].trim();
                    String taskDue = taskArr[1].trim();
                    Task newDeadline = new Deadline(taskName, taskDue);
                    list.addTask(newDeadline);

                } catch (MissingDuedateException e) {
                    System.out.println(e.getMessage());
                    input = inputScanner.nextLine();
                    continue;
                }
            }

            else if (instruction == Instruction.EVENT) {
                try {
                    if (!commandArg.contains("/from")) {
                        throw new MissingEventTimeException("Please specify a start date using '/from'!\n");
                    } else if (!commandArg.contains("/to")) {
                        throw new MissingEventTimeException("Please specify an end date using '/to'!\n");
                    }

                    String[] taskArr = commandArg.split("/from", 2);
                    String taskName = taskArr[0].trim();
                    String taskDur = taskArr[1];

                    String[] taskTime = taskDur.split("/to", 2);
                    String taskFrom = taskTime[0].trim();
                    String taskTo = taskTime[1].trim();

                    Task newEvent = new Event(taskName, taskFrom, taskTo);
                    list.addTask(newEvent);

                } catch(MissingEventTimeException e) {
                    System.out.println(e.getMessage());
                    input = inputScanner.nextLine();
                    continue;
                }
            }

            input = inputScanner.nextLine();
        }
        bye();
    }

    private void greet() {
        System.out.println(
                "Greetings from\n" + haroLogo + "\n" +
                openingMsg + "\n" +
                horizontalLine
        );
    }
    private void bye() {
        System.out.println(closingMSg + "\n" +
                horizontalLine);
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

