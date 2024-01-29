import java.util.Scanner;

public class Duke {
    private static final int TASKS_MAX = 100;
    private static final Task[] tasks = new Task[TASKS_MAX];
    private static int taskCount = 0;
    public static void main(String[] args) {
        String logo = " _  _   __    ____  ____ \n" +
                "( \\/ ) /__\\  (  _ \\(  _ \\\n" +
                " \\  / /(__)\\  )   / )   /\n" +
                " (__)(__)(__)(_)\\_)(_)\\_)\n";

        System.out.println(logo);
        printDivider();
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                handleInput(scanner);
            } catch (IllegalArgumentException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
            } catch (ExitProgramException e) {
                printDivider();
                System.out.println(e.getMessage());
                printDivider();
                break;
            }
        }

    }
    private static void printDivider(){
        int length = 90;
        char divider = 0x2500 ;
        for (int i = 0; i < length; i++) {
            System.out.print(divider);
        }
        System.out.println();
    }

    // Solution below adapted from https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/
    private static void handleInput(Scanner scanner) {
        String input = scanner.nextLine();
        String[] wordArray = input.split(" ", 0);
        // Handle inputs
        if (input.equals("bye")) {
            throw new ExitProgramException("Fair winds to ye, me hearty! May the tide carry ye safely until our paths cross again.");
        } else if (input.equals("list")) {
            printDivider();
            System.out.println("Behold, yer roster of endeavors!");
            for (int i = 0; i < taskCount; i++) {
                String tempNum = Integer.toString(i + 1);
                System.out.println(tempNum + "." + tasks[i].toString());
            }
            printDivider();
        } else if (wordArray[0].equals("mark") || wordArray[0].equals("unmark")) {
            if (wordArray.length != 2) {
                throw new IllegalArgumentException("Expected 2 arguments");
            } else {
                try {
                    int tempIndex = Integer.parseInt(wordArray[1]);
                    if (tempIndex > taskCount) {
                        throw new IllegalArgumentException("Expected integer between 1 and " + taskCount + " as the second argument");
                    } else {
                        printDivider();
                        if (wordArray[0].equals("mark")) {
                            tasks[tempIndex - 1].markAsDone();
                            System.out.println("X marks the spot. I've crossed this task of yer list, me heartie!");
                        } else {
                            tasks[tempIndex - 1].markAsNotDone();
                            System.out.println("The winds be shiftin', and I be lettin' this task sail with the breeze unmarked.");
                        }
                        System.out.println(tasks[tempIndex - 1].toString());
                        printDivider();
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Expected integer between 1 and " + taskCount + " as the second argument");
                }
            }
        }
        else if (wordArray[0].equals("todo")){
            if (wordArray.length < 2) {
                throw new IllegalArgumentException("To-dos must be declared with a description in the format: 'todo *'");
            }
            String tempString = input.substring(5).trim();
            printDivider();
            tasks[taskCount] = new Todo(tempString);
            taskCount += 1;
            System.out.println("added: " + tasks[taskCount - 1].toString());
            printDivider();
        } else if (wordArray[0].equals("deadline")) {
            String tempString = input.substring(9).trim();
            String[] tempArray = tempString.split("/by", 0);
            if (tempArray.length == 1) {
                throw new IllegalArgumentException("Deadlines must be declared in the format: 'deadline * /by *'");
            }
            String description = tempArray[0].trim();
            String by = tempArray[1].trim();
            printDivider();
            tasks[taskCount] = new Deadline(description, by);
            taskCount += 1;
            System.out.println("added: " + tasks[taskCount - 1].toString());
            printDivider();
        } else if (wordArray[0].equals("event")) {
            String tempString = input.substring(6).trim();
            String[] tempArray = tempString.split("/from", 0);
            if (tempArray.length == 1) {
                throw new IllegalArgumentException("Events must be declared in the format: 'deadline * /from * /to *'");
            }
            String description = tempArray[0].trim();
            tempString = tempArray[1].trim();
            tempArray = tempString.split("/to", 0);
            if (tempArray.length == 1) {
                throw new IllegalArgumentException("Events must be declared in the format: 'deadline * /from * /to *'");
            }
            String from = tempArray[0].trim();
            String to = tempArray[1].trim();
            printDivider();
            tasks[taskCount] = new Event(description, from, to);
            taskCount += 1;
            System.out.println("added: " + tasks[taskCount - 1].toString());
            printDivider();
        } else {
            throw new IllegalArgumentException("Sorry, I didn't understand that");
        }
    }
}

// Custom Exit Loop Exception class
class ExitProgramException extends RuntimeException {
    public ExitProgramException(String message) {
        super(message);
    }
}