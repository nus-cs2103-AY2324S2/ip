import java.util.Scanner;
import java.lang.Integer;
import java.lang.NumberFormatException;
public class Haro {
    private String haroLogo = " ___  ___  ________  ________  ________ \n"
            + "|\\  \\|\\  \\|\\   __  \\|\\   __  \\|\\   __  \\ \n"
            + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \n"
            + " \\ \\   __  \\ \\   __  \\ \\   _  _\\ \\  \\\\\\  \\ \n"
            + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \n"
            + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ \n"
            + "    \\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\|_______| \n";
    private String horizontalLine = "______________________________________________ \n";
    private String openingMsg = "Heya! I'm Haro! \n" + "What can I do for you today?";
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
                if (inputArr.length > 0) {
                    if (inputArr[0].equals("")) {
                        System.out.println( horizontalLine + "Empty command! Type something!\n" + horizontalLine);
                        input = inputScanner.nextLine();
                        continue;
                    } else {
                        System.out.println(horizontalLine + "Sorry, please type a valid command\n" + horizontalLine);
                        input = inputScanner.nextLine();
                        continue;
                    }
                }

                else {
                    System.out.println( horizontalLine + "Empty command! Type something!" + horizontalLine);
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
            String commandArg = "";

            if (inputArr.length < 2 || inputArr[1].equals("")) {
                System.out.println(horizontalLine);
                System.out.println("Please input an argument");
                System.out.println(horizontalLine);
                input = inputScanner.nextLine();
                continue;
            }

            commandArg = inputArr[1];

            if (instruction == Instruction.MARK) {
                if (!isNumeric(commandArg)) {
                    System.out.println(horizontalLine);
                    System.out.println("Please input a number for the task you want to mark!");
                    System.out.println(horizontalLine);
                }

                else {
                    int taskNumber = Integer.parseInt(commandArg) - 1;
                    list.markTask(taskNumber);
                }
            }

            else if (instruction == Instruction.UNMARK) {
                if (!isNumeric(commandArg)) {
                    System.out.println(horizontalLine);
                    System.out.println("Please input a number for the task you want to unmark!\n");
                    System.out.println(horizontalLine);
                }

                else {
                    int taskNumber = Integer.parseInt(commandArg) - 1;
                    list.unmarkTask(taskNumber);
                }
            }

            else if (instruction == Instruction.TODO) {
                Task newTodo = new ToDo(commandArg.trim());
                list.addTask(newTodo);
            }

            else if (instruction == Instruction.DEADLINE) {
                if (!commandArg.contains("/by")) {
                    System.out.println(horizontalLine + "Please specify a due date using '/by'!\n" + horizontalLine);
                } else {
                    String[] taskArr = commandArg.split("/by", 2);
                    String taskName = taskArr[0].trim();
                    String taskDue = taskArr[1].trim();
                    Task newDeadline = new Deadline(taskName, taskDue);
                    list.addTask(newDeadline);
                }
            }

            else if (instruction == Instruction.EVENT) {
                if (!commandArg.contains("/from")) {
                    System.out.println(horizontalLine + "Please specify a start date using '/from'!\n" + horizontalLine);
                } else if (!commandArg.contains("/to")) {
                    System.out.println(horizontalLine + "Please specify an end date using '/to'!\n" + horizontalLine);
                } else {
                    String[] taskArr = commandArg.split("/from", 2);
                    String taskName = taskArr[0].trim();
                    String taskDur = taskArr[1];
                    String[] taskTime = taskDur.split("/to", 2);
                    String taskFrom = taskTime[0].trim();
                    String taskTo = taskTime[1].trim();

                    Task newEvent = new Event(taskName, taskFrom, taskTo);
                    list.addTask(newEvent);
                }
            }

            input = inputScanner.nextLine();
        }
        bye();
    }

    private void greet() {
        System.out.println(
                "Greetings from\n" + haroLogo + "\n" +
                horizontalLine +
                openingMsg + "\n" +
                horizontalLine
        );
    }
    private void bye() {
        System.out.println(horizontalLine + closingMSg + "\n" +
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

