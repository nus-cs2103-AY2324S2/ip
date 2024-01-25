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
                    } else {
                        Task newTask = new Task(input);
                        list.addTask(newTask);
                        System.out.println(horizontalLine + "added: " + input + "\n" + horizontalLine);
                    }
                }

                else {
                    System.out.println( horizontalLine + "Empty command! Type something!" + horizontalLine);
                }
            }

            else if (instruction == Instruction.LIST) {
                list.printList();
            }

            // Commands with arguments
            String commandArg = "";

            if (inputArr.length >= 2) {
                commandArg = inputArr[1];
            }

            if (instruction == Instruction.MARK) {
                if (inputArr.length < 2) {
                    System.out.println(horizontalLine);
                    System.out.println("Please input task number that you want to mark!");
                    System.out.println(horizontalLine);
                }

                else if (!isNumeric(commandArg)) {
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
                if (inputArr.length < 2) {
                    System.out.println(horizontalLine);
                    System.out.println("Please input task number that you want to unmark!\n");
                    System.out.println(horizontalLine);
                }

                else if (!isNumeric(commandArg)) {
                    System.out.println(horizontalLine);
                    System.out.println("Please input a number for the task you want to unmark!\n");
                    System.out.println(horizontalLine);
                }

                else {
                    int taskNumber = Integer.parseInt(commandArg) - 1;
                    list.unmarkTask(taskNumber);
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

