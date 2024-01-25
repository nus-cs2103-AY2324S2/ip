import java.util.Scanner;
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
                default:
                    instruction = Instruction.NONE;
            }

            if (instruction == Instruction.BYE) {
                break;
            }

            else if (instruction == Instruction.NONE) {
                if (inputArr.length > 0) {
                    list.addTask(input);
                    System.out.println(horizontalLine + "added: " + input + "\n" + horizontalLine);
                }
                else {
                    System.out.println("Empty command! Type something!");
                }
            }

            else if (instruction == Instruction.LIST) {
                list.printList();
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
}

